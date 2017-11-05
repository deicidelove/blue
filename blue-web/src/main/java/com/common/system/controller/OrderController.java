/**
 * 
 */
package com.common.system.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.ScheduleDto;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BluePation;
import com.common.system.entity.BlueShift;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DeptService;
import com.common.system.service.DoctorSchedulEService;
import com.common.system.service.DoctorService;
import com.common.system.service.OppointmentService;
import com.common.system.util.CookieUtil;
import com.common.system.util.DateUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
public class OrderController {

	@Resource
	private CommonService commonService;

	@Resource
	private DoctorService doctorService;

	@Resource
	private DoctorSchedulEService doctorSchedulEService;
	
	@Resource 
	private OppointmentService oppointmentService;

	@Resource
	private DeptService deptService;

	@RequestMapping("/orderPage")
	public ModelAndView orderPage(ModelAndView modelAndView) {

		List<BlueStaff> staffs = commonService.findStaff();
		modelAndView.addObject("doctors", staffs);
		modelAndView.setViewName("/html/orderList");
		return modelAndView;
	}

	@RequestMapping(value = "doctorDetial/{sid}", method = RequestMethod.GET)
	public ModelAndView doctorDetial(ModelAndView modelAndView,
			@PathVariable Integer sid) throws ParseException {
		Result<BlueStaff> ruseult = doctorService.findDoctor(sid);
		modelAndView.addObject("doctor", ruseult.getData());
		Date firstDay = DateUtil.firstDay();
		Date endDay = DateUtil.endDay();
		// 上午
		List<BlueDoctorSchedule> scheduleMorning = doctorSchedulEService
				.findByDateAndTime("上午", sid, DateUtil.formtString(firstDay), DateUtil.formtString(endDay));
		// 下午
		List<BlueDoctorSchedule> scheduleAfternoon = doctorSchedulEService
				.findByDateAndTime("下午", sid, DateUtil.formtString(firstDay), DateUtil.formtString(endDay));

		modelAndView.addObject("morning", setScheduleDto(scheduleMorning));
		modelAndView.addObject("afternoon", setScheduleDto(scheduleAfternoon));
		modelAndView.setViewName("/html/orderDoctor");
		return modelAndView;
	}

	@RequestMapping(value = "doctorOrder")
	@ResponseBody
	public Result<List<BlueShift>> doctorOrder(Integer scheduleId) {
		List<BlueShift> result = commonService.findBlueShift(scheduleId);
		return new Result<List<BlueShift>>(true, "获取成功！", result);

	}

	@RequestMapping(value = "orderInfo/{scheduleId}", method = RequestMethod.GET)
	public ModelAndView orderInfo(ModelAndView modelAndView, HttpServletRequest request,@PathVariable Integer scheduleId) throws ParseException {
		BlueDoctorSchedule bds = doctorSchedulEService.findBySid(
				scheduleId).getData();
		BlueStaff staff = doctorService.findDoctor(bds.getStaffId()).getData();
		BlueDept dept = deptService.findBySid(bds.getDeptId()).getData();
		modelAndView.addObject("orderTime", DateUtil.formtString(bds.getCreateTime()));
		modelAndView.addObject("bds", bds);
		modelAndView.addObject("staff", staff);
		modelAndView.addObject("dept", dept);
		
		String userId= CookieUtil.getCookieValue(request, "openId");
		List<BluePation> pations = commonService.findPations(userId);
		
		modelAndView.addObject("pations", pations);
		modelAndView.setViewName("/html/orderInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "orderSubmit")
	@ResponseBody
	public Result<Integer> orderSubmit(HttpServletRequest request ,Integer staffId,String staffName,String pay,String deptName,Integer pationId,Integer deptId,String orderTime) throws ParseException {
		String userId= CookieUtil.getCookieValue(request, "openId");
		Result<Integer> result = oppointmentService.addOppo(staffId, orderTime, pay,userId,pationId);
		return result;

	}

	private List<ScheduleDto> setScheduleDto(List<BlueDoctorSchedule> schedule) {
		List<ScheduleDto> list = new ArrayList<>();
		try {

			// 从周一到周日遍历
			for (int i = 0; i < 7; i++) {
				String date = DateUtil.formtString(DateUtil.getWantDay(i));
				ScheduleDto dto = new ScheduleDto();
				for (BlueDoctorSchedule blueDoctorSchedule : schedule) {
					String blueDate = DateUtil.formtString(blueDoctorSchedule
							.getCreateTime());

					if (blueDate.equals(date)) {
						if (blueDoctorSchedule.getCount() > 0) {
							dto.setFlag(0);
						} else {
							dto.setFlag(1);
						}
						dto.setScheduleId(blueDoctorSchedule.getSid());
						schedule.remove(blueDoctorSchedule);
						break;
					}

				}
				if (dto.getFlag() == null) {
					dto.setFlag(2);
				}
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}

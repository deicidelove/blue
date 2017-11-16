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
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dao.ShiftDao;
import com.common.system.dto.OpposDto;
import com.common.system.dto.OrderDto;
import com.common.system.dto.ScheduleDto;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BlueOppointment;
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
	
	@Resource
	private ShiftDao shiftDao;

	@RequestMapping("/orderPage")
	public ModelAndView orderPage(ModelAndView modelAndView) {

		List<BlueStaff> staffs = commonService.findStaff();
		modelAndView.addObject("doctors", staffs);
		modelAndView.setViewName("/html/orderList");
		return modelAndView;
	}
	@RequestMapping(value ="orderPageByDate")
	@ResponseBody
	public Result<List<BlueStaff>> orderPageByDate(String date) throws ParseException {
		
		List<BlueDoctorSchedule> schedules = doctorSchedulEService.findBlueDoctorSchedule(date);
		List<Integer> staffIds = new ArrayList<>();
		for (BlueDoctorSchedule dto : schedules) {
			staffIds.add(dto.getStaffId());
		}
 		List<BlueStaff> staffs = commonService.findStaff(staffIds);
		return new Result<>(true, "获取成功！", staffs);
	}

	@RequestMapping(value = "doctorDetial/{sid}/{oppoId}", method = RequestMethod.GET)
	public ModelAndView doctorDetial(ModelAndView modelAndView,
			@PathVariable Integer sid,@PathVariable Integer oppoId, HttpServletResponse response) throws ParseException {
		CookieUtil.setCookie(response, "oppoId", String.valueOf(oppoId));
		Result<BlueStaff> ruseult = doctorService.findDoctor(sid);
		modelAndView.addObject("doctor", ruseult.getData());
		Date firstDay = DateUtil.formtString(DateUtil.getdate( 0));
		Date endDay = DateUtil.formtString(DateUtil.getdate(6));
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
	public Result<OrderDto> doctorOrder(Integer scheduleId) throws ParseException {
		BlueDoctorSchedule bds = doctorSchedulEService.findBySid(
				scheduleId).getData();
		List<BlueShift> result = commonService.findBlueShift(scheduleId);
		OrderDto dto = new OrderDto();
		dto.setDate(DateUtil.formtString(bds.getCreateTime()));
		dto.setShifts(result);
		return new Result<OrderDto>(true, "获取成功！", dto);
	}

	@RequestMapping(value = "orderInfo/{scheduleId}/{shiftsId}", method = RequestMethod.GET)
	public ModelAndView orderInfo(ModelAndView modelAndView,HttpServletResponse response,HttpServletRequest request,@PathVariable Integer scheduleId,@PathVariable Integer shiftsId) throws ParseException {
		BlueDoctorSchedule bds = doctorSchedulEService.findBySid(
				scheduleId).getData();
		BlueStaff staff = doctorService.findDoctor(bds.getStaffId()).getData();
		BlueDept dept = deptService.findBySid(bds.getDeptId()).getData();
		modelAndView.addObject("orderTime", DateUtil.formtString(bds.getCreateTime()));
		modelAndView.addObject("bds", bds);
		modelAndView.addObject("staff", staff);
		modelAndView.addObject("dept", dept);
		shiftDao.updateShiftStatus(shiftsId);
		String userId= CookieUtil.getCookieValue(request, "openId");
		List<BluePation> pations = commonService.findPations(userId);
		modelAndView.addObject("pations", pations);
		modelAndView.setViewName("/html/orderInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "orderSubmit")
	@ResponseBody
	public Result<Integer> orderSubmit(HttpServletResponse response,HttpServletRequest request ,Integer staffId,String staffName,String pay,String deptName,Integer pationId,Integer deptId,String orderTime,Integer scheduleId) throws ParseException {
		String userId = CookieUtil.getCookieValue(request, "openId");
		String oppoId = CookieUtil.getCookieValue(request, "oppoId");
		if(!"-1".equals(oppoId)){
			oppointmentService.deleteOppo(Integer.valueOf(oppoId));
			CookieUtil.setCookie(response, "oppoId", null);
		}
		Result<Integer> result = oppointmentService.addOppo(staffId, orderTime, pay,userId,pationId,scheduleId);
		return result;
	}

	private List<ScheduleDto> setScheduleDto(List<BlueDoctorSchedule> schedule) {
		List<ScheduleDto> list = new ArrayList<>();
		try {

			// 从当前日遍历到后七天
			for (int i = 0; i < 7; i++) {
				String date = DateUtil.getdate(i);
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
	
	/**
	 * 我的预约
	 * @param modelAndView
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/myOrderList")
	@ResponseBody
	public ModelAndView myOrderList(HttpServletRequest request ,ModelAndView modelAndView) throws ParseException{
		String userId = CookieUtil.getCookieValue(request, "openId");
		List<BlueOppointment> oppos = oppointmentService.findByUserId(userId);
		List<OpposDto> oppoDtos = new ArrayList<>();
		for (BlueOppointment blueOppointment : oppos) {
			OpposDto dto = new OpposDto();
			dto.setSid(blueOppointment.getSid());
			dto.setContext(blueOppointment.getContext());
			dto.setCreateTime(blueOppointment.getCreateTime());
			dto.setDeptId(blueOppointment.getDeptId());
			dto.setDeptName(blueOppointment.getDeptName());
			dto.setOrderTime(blueOppointment.getOrderTime());
			dto.setPationId(blueOppointment.getPationId());
			dto.setPayMoney(blueOppointment.getPayMoney());
			dto.setStaffId(blueOppointment.getStaffId());
			dto.setStaffName(blueOppointment.getStaffName());
			dto.setUserId(blueOppointment.getUserId());
			BlueStaff staff = doctorService.findDoctor(blueOppointment.getStaffId()).getData();
			dto.setHead_url(staff.getHeadUrl()==null?"":staff.getHeadUrl());
			dto.setIntroduce(staff.getIntroduce());
			dto.setPositionName(staff.getPositionName());
			String date = blueOppointment.getOrderTime().substring(0, 10);
			Date orderDate = DateUtil.formtString(date);
			Date now = DateUtil.formtString(DateUtil.getdate( 0));
			if(orderDate.compareTo(now) < 0){
				dto.setIsPass(1);
			}else{
				dto.setIsPass(0);
			}
			oppoDtos.add(dto);
		}
		modelAndView.addObject("oppos", oppoDtos);
		modelAndView.setViewName("/personal/myOrder");
		return modelAndView;
	}
	
	@RequestMapping(value = "deleteOppo")
	@ResponseBody
	public Result<Integer> deleteOppo(Integer sid) {
		Result<Integer> result = oppointmentService.deleteOppo(sid);
		return result;

	}

}

/**
 * 
 */
package com.common.system.controller;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DoctorSchedulEService;
import com.common.system.service.DoctorService;
import com.common.system.util.ImportExcelUtil;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("doctorSchedule")
public class DoctorScheduleController {

	@Resource
	private DoctorSchedulEService doctorSchedulEService;

	@Resource
	private CommonService commonService;

	@Resource
	private ImportExcelUtil importExcelUtil;
	
	@Resource
	private DoctorService doctorService;
	
	@Value("${schedule.count}")
	private String scheduleCount;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(DoctorScheduleController.class);
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/system/admin/doctorSchedule/list");
		return modelAndView;
	}

	/**
	 * 获取医生排班列表(某天按科室查找)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueDoctorSchedule> getDoctorList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "DoctorSchedule_tab_length", required = false)String DoctorSchedule_tab_length) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return doctorSchedulEService.getDoctorList(start, pageSize, date,
				search);
	}

	/**
	 * 删除某个排班
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = doctorSchedulEService.delete(id);
		return result;
	}

	/**
	 * 添加页面数据渲染
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/doctorSchedule/add");
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs = commonService.findStaff();
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("staffs", staffs);
		return modelAndView;
	}
	
	/**
	 * 批量添加页面数据渲染
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "batchAdd", method = RequestMethod.GET)
	public ModelAndView batchAdd(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/doctorSchedule/batchAdd");
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs = commonService.findStaff();
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("staffs", staffs);
		return modelAndView;
	}
	/**
	 * 保存
	 * @param date
	 * @param count
	 * @param staffId
	 * @param shiftTime
	 * @return
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
	Result<Integer> save(String date, String count, int staffId,
			String shiftTime) {
		return doctorSchedulEService.save(date, count, staffId, shiftTime);
	}
	
	@RequestMapping(value = "batchSave",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Result<Integer> batchSave(@RequestParam("fileName") MultipartFile file) {
		Result<Integer> result = new Result<Integer>();
		StringBuffer sb = new StringBuffer();
		try (InputStream inpustStr =file.getInputStream()){
			List<List<Object>> excelList = importExcelUtil.getBankListByExcel(inpustStr);
			if(CollectionUtils.isEmpty(excelList)){
				result.setMsg("请确认表格是否为空表！");
				return result;
			}
			//第一行日期
			List<Object> firstList = excelList.get(0);
			
			for(int rows= 1; rows< excelList.size(); rows++){
				List<Object> rowList = excelList.get(rows);
				Result<BlueStaff> resultStaff = null;
				for(int cels = 0; cels <rowList.size(); cels++){
					//人名
					if(cels == 0 ){
						String name = String.valueOf(rowList.get(cels));
						//查询staffId
						resultStaff = doctorService.findDoctorByName(String.valueOf(rowList.get(cels)));
						if(null == resultStaff.getData()){
							sb.append(name +"不存在，请添加！|");
							break;
						}
					}
					if(cels != 0){
						if("值班".equals(String.valueOf(rowList.get(cels)))){
							String date = String.valueOf(firstList.get(cels));
							Result<Integer> resultSF = doctorSchedulEService.save(date, scheduleCount, resultStaff.getData().getSid(), "上午");
							if(!resultSF.isStatus()){
								sb.append(resultSF.getMsg()+"|");
							}
							Result<Integer> resultXF = doctorSchedulEService.save(date, scheduleCount, resultStaff.getData().getSid(), "下午");
							if(!resultXF.isStatus()){
								sb.append(resultXF.getMsg()+"|");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("import excel error !" , e );
			result.setMsg("系统异常，请联系管理员！");
			return result;
		}
		result.setCode(MsgCode.SUCCESS);
		result.setStatus(true);
		result.setMsg(StringUtils.isEmpty(sb.toString())?"保存成功！": sb.toString());
		return result;
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param modelAndView
	 * @return
	 */
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueDoctorSchedule> result = doctorSchedulEService.findBySid(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/doctorSchedule/edit");
	        return modelAndView;
	    }
	 /**
	  * 更新
	  * @param name
	  * @param deptId
	  * @param sex
	  * @param phone
	  * @param sid
	  * @param introduce
	  * @return
	  */
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(String date, String count,
				String shiftTime,int id){	       
	        return doctorSchedulEService.update(date, count, 0, shiftTime, id);
	    }

}

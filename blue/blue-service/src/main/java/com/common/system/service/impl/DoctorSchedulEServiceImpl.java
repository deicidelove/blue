/**
 * 
 */
package com.common.system.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.DeptDao;
import com.common.system.dao.DoctorScheduleDao;
import com.common.system.dao.StaffDao;
import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BlueStaff;
import com.common.system.service.DoctorSchedulEService;
import com.common.system.util.DateUtil;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("doctorSchedulEService")
public class DoctorSchedulEServiceImpl implements DoctorSchedulEService {

	private Logger LOG = LoggerFactory.getLogger(DoctorSchedulEServiceImpl.class);
	
	@Resource
	private DoctorScheduleDao doctorScheduleDao;
	
	@Resource
	private StaffDao staffDao;
	
	@Resource
	private DeptDao deptDao;
	
	
	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorSchedulEService#getDoctorList(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean<BlueDoctorSchedule> getDoctorList(int start, int limit, String date,
			String deptId) {
		try {
			int count = doctorScheduleDao.findCount(date, deptId);
			List<BlueDoctorSchedule> doctorList = doctorScheduleDao.findList(start*limit, limit, date, deptId);
			PageBean<BlueDoctorSchedule> pageList = new PageBean<BlueDoctorSchedule>();
			pageList.setiDisplayStart(start+1);
			pageList.setiDisplayLength(limit);
			pageList.setData(doctorList);
			pageList.setiTotalDisplayRecords(count);
			return pageList;
		} catch (Exception e) {
			LOG.error("获取医生排班列表失败！deptId:{},msg:{}", deptId, e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorSchedulEService#delete(java.lang.Integer)
	 */
	@Override
	public Result<Integer> delete(Integer id) {
		try {
			int count = doctorScheduleDao.delete(id);
			return new Result<Integer>(true,"删除成功!",count);
		} catch (Exception e) {
			LOG.error("删除排班失败！msg:{};sid:{}",e,id);
		}
		return new Result<Integer>(false,"删除失败!",0);
	}

	@Override
	public Result<Integer> save(String date, String count, int staffId,String shiftTime) {
		try {
			BlueDoctorSchedule dto = this.setBlueDoctorSchedule(date, count, staffId, shiftTime);
			int num = doctorScheduleDao.save(dto);
			return new Result<Integer>(true,"保存成功!",num);
		} catch (Exception e) {
			LOG.error("保存排班失败！msg:{}",e);
		}
		return new Result<Integer>(false,"保存失败!",null);
	}

	@Override
	public Result<BlueDoctorSchedule> findBySid(int sid) {
		try {
			BlueDoctorSchedule dto = doctorScheduleDao.findBySid(sid);
			return new Result<BlueDoctorSchedule>(true,"查询成功！",dto);
		} catch (Exception e) {
			LOG.error("查询医生排班失败！msg:{};sid:{}",e,sid);
		}
		return new Result<BlueDoctorSchedule>(false,"查询失败！",null);
	}

	@Override
	public Result<Integer> update(String date, String count, int staffId,
			String shiftTime, int sid) {
		try {
			BlueDoctorSchedule dto = this.setBlueDoctorSchedule(date, count, staffId, shiftTime);
			dto.setSid(sid);
			int num = doctorScheduleDao.update(dto);
			return new Result<Integer>(true,"更新成功!",num);
		} catch (Exception e) {
			LOG.error("更新排班失败！msg:{},sid:{}",e,sid);
		}
		return new Result<Integer>(false,"更新失败!",null);
	}
	
	private BlueDoctorSchedule setBlueDoctorSchedule(String date, String count, int staffId,
			String shiftTime) throws ParseException{
		BlueDoctorSchedule dto = new BlueDoctorSchedule();
		dto.setCount(Integer.valueOf(count));
		dto.setStaffId(staffId);
		dto.setCreateTime(DateUtil.formtString(date));
		BlueStaff staff = staffDao.findBySid(staffId);
		if(staff != null){
			dto.setStaffName(staff.getName());
			dto.setDeptName(staff.getDeptName());
			dto.setDeptId(staff.getDeptId());
		}
		dto.setShiftTime(shiftTime);
		return dto;
		
	}

}

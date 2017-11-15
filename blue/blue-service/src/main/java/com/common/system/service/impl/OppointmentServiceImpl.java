/**
 * 
 */
package com.common.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.OppointmentDao;
import com.common.system.dao.ShiftDao;
import com.common.system.dao.StaffDao;
import com.common.system.entity.BlueOppointment;
import com.common.system.entity.BlueStaff;
import com.common.system.service.OppointmentService;
import com.common.system.util.DateUtil;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("oppointmentService")
public class OppointmentServiceImpl implements OppointmentService {
	
	private Logger LOG = LoggerFactory.getLogger(OppointmentServiceImpl.class);
	
	@Resource
	private OppointmentDao oppointmentDao;
	
	@Resource
	private StaffDao staffDao;
	
	@Resource
	private ShiftDao shiftDao;

	/* (non-Javadoc)
	 * @see com.common.system.service.OppointmentService#getOppointmentList(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public PageBean<BlueOppointment> getOppointmentList(String type,
			int startPage, int limitLength, String date) {
		try {
			int count = oppointmentDao.findCount(date, type);
			List<BlueOppointment> dto = oppointmentDao.findList(startPage, limitLength, date, type);
			PageBean<BlueOppointment> pageList = new PageBean<BlueOppointment>();
			pageList.setiDisplayStart(startPage);
			pageList.setiDisplayLength(limitLength);
			pageList.setData(dto);
			pageList.setiTotalDisplayRecords(count);
			return pageList;
		} catch (Exception e) {
			LOG.error("获取预约列表失败！type:{},msg:{}", type, e);
			return null;
		}
	}

	@Override
	public Result<Integer> addOppo(int staffId, String date, String payMoney,String userId,
			Integer pationId,Integer scheduleId) {
		try {
			BlueOppointment oppo = new BlueOppointment();
			oppo.setStaffId(staffId);
			BlueStaff staff = staffDao.findBySid(staffId);
			if(staff!=null){
				oppo.setStaffName(staff.getName());
				oppo.setDeptId(staff.getDeptId());
				oppo.setDeptName(staff.getDeptName());
			}
			oppo.setPayMoney(Float.valueOf(payMoney));
			oppo.setOrderTime(date);
			oppo.setPationId(pationId);
			oppo.setUserId(userId);
			oppo.setCreateTime(new Date());
			int count = oppointmentDao.save(oppo);
			shiftDao.updateShift(scheduleId);
			return new Result<Integer>(true,"保存成功！",count);
			
		} catch (Exception e) {
			LOG.error("保存失败！msg:{}", e);
		}
		return new Result<>(false,"保存失败！",0);
	}

	@Override
	public Result<Integer> deleteOppo(int sid) {
		try {
			int count = oppointmentDao.delete(sid);
			return new Result<Integer>(true,"删除成功!",count);
		} catch (Exception e) {
			LOG.error("删除预约失败！msg:{};sid:{}",e,sid);
		}
		return new Result<Integer>(false,"删除失败!",0);
	}

	@Override
	public Result<Integer> updateOppo(String date, int sid) {
		try {
			BlueOppointment oppo = new BlueOppointment();
			oppo.setOrderTime(date);
			oppo.setSid(sid);
			int count = oppointmentDao.update(oppo);
			return new Result<Integer>(true,"更新成功！",count);
		} catch (Exception e) {
			LOG.error("更新失败！msg:{}", e);
		}
		return new Result<>(false,"更新失败！",0);
	}

	@Override
	public Result<BlueOppointment> findOppo(int sid) {
		try {
			BlueOppointment oppo = oppointmentDao.findBySid(sid);
			return new Result<BlueOppointment>(true,"查询成功！",oppo);
		} catch (Exception e) {
			LOG.error("查询医生失败！msg:{};sid:{}",e,sid);
		}
		return new Result<BlueOppointment>(false,"查询失败！",null);
	}

}

/**
 * 
 */
package com.common.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.DeptDao;
import com.common.system.dao.RoleDao;
import com.common.system.dao.StaffDao;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueStaff;
import com.common.system.entity.RcRole;
import com.common.system.service.DoctorService;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
	
	private Logger LOG = org.slf4j.LoggerFactory.getLogger(DoctorServiceImpl.class);

	@Resource
	private StaffDao staffDao;
	
	@Resource
	private DeptDao deptDao;
	
	@Resource
	private RoleDao roleDao;
	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorService#getDoctorList(int, int, int)
	 */
	@Override
	public PageBean<BlueStaff> getDoctorList(String type, int startPage,
			int limitLength,String date) {
		
		try {
			int count = staffDao.findAllCount(date, type);
			List<BlueStaff> doctortList = staffDao.findAllStaff(date,type,startPage, limitLength);
			PageBean<BlueStaff> pageList = new PageBean<BlueStaff>();
			pageList.setiDisplayStart(startPage);
			pageList.setiDisplayLength(limitLength);
			pageList.setData(doctortList);
			pageList.setiTotalDisplayRecords(count);
			return pageList;
		} catch (Exception e) {
			LOG.error("获取医生列表失败！type:{},msg:{}", type, e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorService#deleteDoctor(int)
	 */
	@Override
	public Result<Integer> deleteDoctor(int sid) {
		try {
			int count = staffDao.deleteStaff(sid);
			return new Result<Integer>(true,"删除成功!",count);
		} catch (Exception e) {
			LOG.error("删除医生失败！msg:{};sid:{}",e,sid);
		}
		return new Result<Integer>(false,"删除失败!",0);
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorService#addDoctor(int, java.lang.String, int, int, java.lang.String, int, java.lang.String)
	 */
	@Override
	public Result<Integer> addDoctor(String jobNum,String name, int deptId,int sex,String phone,String introduce,String address,String callFee,String positionName,MultipartFile file) {
		try {
			String urlTream =PicUtil.upFile(file);
			BlueStaff staff = new BlueStaff();
			staff.setHeadUrl(urlTream);
			staff.setCallFee(Double.valueOf(callFee));
			staff.setPositionName(positionName);
			staff.setName(name);
			staff.setPassword("111111");
			staff.setJobNum(jobNum);
			staff.setDeptId(deptId);
			BlueDept dept = deptDao.findBySid(deptId);
			if(dept!=null){
				staff.setDeptName(dept.getName());
			}
			staff.setIntroduce(introduce);
			staff.setSex(sex);
			staff.setPhone(phone);
			RcRole role=roleDao.findDoctorId();
			if(role!=null){
				staff.setPositionId(role.getId());
			}
			staff.setCreateTime(new Date());
			staff.setAddress(address);
			int count = staffDao.addStaff(staff);
			return new Result<Integer>(true,"更新成功！",count);
		} catch (Exception e) {
			LOG.error("更新失败！msg:{}", e);
		}
		return new Result<>(false,"更新失败！",0);
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorService#findDoctor(int)
	 */
	@Override
	public Result<BlueStaff> findDoctor(int sid) {
		try {
			BlueStaff staff = staffDao.findBySid(sid);
			return new Result<BlueStaff>(true,"查询成功！",staff);
		} catch (Exception e) {
			LOG.error("查询医生失败！msg:{};sid:{}",e,sid);
		}
		return new Result<BlueStaff>(false,"查询失败！",null);
	}

	@Override
	public Result<BlueStaff> findDoctorByName(String name) {
		try {
			BlueStaff staff = staffDao.findByName(name);
			return new Result<BlueStaff>(true,"查询成功！",staff);
		} catch (Exception e) {
			LOG.error("查询医生失败！msg:{};sid:{}",e,name);
		}
		return new Result<BlueStaff>(false,"查询失败！",null);
	}
	
	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorService#updateDoctor(int, java.lang.String, int, int, java.lang.String, int, java.lang.String, int)
	 */
	@Override
	public Result<Integer> updateDoctor(MultipartFile file,String name, int deptId,
			int sex, String phone, int level, int sid,String introduce,String jobNum,String address,String positionName) {
		try {
			String url = PicUtil.upFile(file);
			BlueStaff staff = new BlueStaff();
			staff.setSid(sid);
			staff.setHeadUrl(url);
			staff.setName(name);
			staff.setDeptId(deptId);
			staff.setPositionName(positionName);
			BlueDept dept = deptDao.findBySid(deptId);
			if(dept!=null){
				staff.setDeptName(dept.getName());
			}
			staff.setJobNum(jobNum);
			staff.setIntroduce(introduce);
			staff.setSex(sex);
			staff.setPhone(phone);
			RcRole role=roleDao.findDoctorId();
			if(role!=null){
				staff.setPositionId(role.getId());
			}
			staff.setAddress(address);
			int count = staffDao.updateStaff(staff);
			return new Result<>(true,"更新成功！",count);
		} catch (Exception e) {
			LOG.error("更新失败！msg:{},sid:{}", e,sid);
		}
		return new Result<>(false,"更新失败！",0);
	}

}

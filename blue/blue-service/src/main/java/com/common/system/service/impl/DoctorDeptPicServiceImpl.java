/**
 * 
 */
package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.DeptDao;
import com.common.system.dao.DeptDoctorPicDao;
import com.common.system.entity.BlueDeptDoctorPic;
import com.common.system.entity.BlueStaff;
import com.common.system.service.DoctorDeptPicService;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("doctorDeptPicService")
public class DoctorDeptPicServiceImpl implements DoctorDeptPicService {
	
	private Logger LOG = org.slf4j.LoggerFactory.getLogger(DoctorDeptPicServiceImpl.class);
	
	@Resource
	private DeptDoctorPicDao deptDoctorPicDao;
	
	@Resource
	private DeptDao deptDao;

	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorDeptPicService#findPics(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public PageBean<BlueDeptDoctorPic> findPics(String type, int startPage,
			int limitLength, String date) {
		try {
			int count = deptDoctorPicDao.findAllCount(date, type);
			List<BlueDeptDoctorPic> doctortList = deptDoctorPicDao.findAllDeptDoctorPic(date, type, startPage, limitLength);
			PageBean<BlueDeptDoctorPic> pageList = new PageBean<BlueDeptDoctorPic>();
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
	 * @see com.common.system.service.DoctorDeptPicService#deletePic(int)
	 */
	@Override
	public Result<Integer> deletePic(int sid) {
		try {
			int count = deptDoctorPicDao.delete(sid);
			return new Result<Integer>(true,"删除成功!",count);
		} catch (Exception e) {
			LOG.error("删除deletePic失败！msg:{};sid:{}",e,sid);
		}
		return new Result<Integer>(false,"删除失败!",0);
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.DoctorDeptPicService#addDoctorDeptPic(java.lang.Integer, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Result<Integer> addDoctorDeptPic(Integer deptId, MultipartFile file) {
		try {
			String url =PicUtil.upFile(file);
			BlueDeptDoctorPic pic = new BlueDeptDoctorPic();
			pic.setUrl(url);
			pic.setDeptId(deptId);
			pic.setDeptName(deptDao.findBySid(deptId).getName());
			int count = deptDoctorPicDao.add(pic);
			return new Result<Integer>(true,"保存成功！",count);
		} catch (Exception e) {
			LOG.error("保存失败！msg:{}", e);
		}
		return new Result<>(false,"保存失败！",0);
	}

	@Override
	public Result<BlueDeptDoctorPic> findDeptPic(int sid) {
		try {
			BlueDeptDoctorPic deptPic = deptDoctorPicDao.findBySid(sid);
			return new Result<BlueDeptDoctorPic>(true,"查询成功！",deptPic);
		} catch (Exception e) {
			LOG.error("查询findDeptPic失败！msg:{};sid:{}",e,sid);
		}
		return new Result<BlueDeptDoctorPic>(false,"查询失败！",null);
	}

	@Override
	public Result<Integer> updateDoctorDeptPic(Integer sid, Integer deptId,
			MultipartFile file) {
		try {
			String url =PicUtil.upFile(file);
			BlueDeptDoctorPic pic = new BlueDeptDoctorPic();
			pic.setSid(sid);
			pic.setUrl(url);
			pic.setDeptId(deptId);
			pic.setDeptName(deptDao.findBySid(deptId).getName());
			int count = deptDoctorPicDao.add(pic);
			return new Result<Integer>(true,"更新成功！",count);
		} catch (Exception e) {
			LOG.error("更新失败！msg:{}", e);
		}
		return new Result<>(false,"更新失败！",0);
	}

}

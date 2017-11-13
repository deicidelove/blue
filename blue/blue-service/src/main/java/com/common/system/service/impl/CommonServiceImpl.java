/**
 * 
 */
package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.AdvertDao;
import com.common.system.dao.DeptDao;
import com.common.system.dao.DeptDoctorPicDao;
import com.common.system.dao.EncyclopediasDao;
import com.common.system.dao.HospitalDao;
import com.common.system.dao.PationDao;
import com.common.system.dao.ProjectAdvertDao;
import com.common.system.dao.ProjectDao;
import com.common.system.dao.ShiftDao;
import com.common.system.dao.StaffDao;
import com.common.system.dao.WorkDao;
import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDeptDoctorPic;
import com.common.system.entity.BlueEncyclopedias;
import com.common.system.entity.BlueHospital;
import com.common.system.entity.BlueNeedWork;
import com.common.system.entity.BluePation;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueProjectAdvert;
import com.common.system.entity.BlueShift;
import com.common.system.entity.BlueSource;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.util.PicUtil;

/**
 * @author amkong
 * 
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Resource
	private DeptDao deptDao;

	@Resource
	private StaffDao staffDao;

	@Resource
	private AdvertDao advertDao;

	@Resource
	private ProjectDao projectDao;

	@Resource
	private HospitalDao hospitalDao;

	@Resource
	private EncyclopediasDao encyclopediasDao;

	@Resource
	private WorkDao workDao;

	@Resource
	private ShiftDao shiftDao;

	@Resource
	private PationDao pationDao;
	
	@Resource
	private DeptDoctorPicDao deptDoctorPicDao;
	
	@Resource
	private ProjectAdvertDao projectAdvertDao;

	private Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.CommonService#findDept()
	 */
	@Override
	public List<BlueDept> findDept() {
		try {
			List<BlueDept> depts = deptDao.findDepts(0, Integer.MAX_VALUE);
			return depts;
		} catch (Exception e) {
			LOG.error("获取所有部门失败！msg:{}", e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.CommonService#findStaff()
	 */
	@Override
	public List<BlueStaff> findStaff() {
		try {
			List<BlueStaff> staffs = staffDao.findAllStaff(null, "-1", 0,
					Integer.MAX_VALUE);
			return staffs;
		} catch (Exception e) {
			LOG.error("获取所有医生失败！msg:{}", e);
			return null;
		}
	}

	@Override
	public String upFile(MultipartFile file, int sid, Object object) {
		try {
			String url = PicUtil.upFile(file);
			if (object instanceof BlueAdvert) {
				((BlueAdvert) object).setSid(sid);
				((BlueAdvert) object).setUrl(url);
				advertDao.updateUrl((BlueAdvert) object);

			} else if (object instanceof BlueSource) {

			} else if (object instanceof BlueProject) {

			} else if (object instanceof BlueHospital) {

			}
			return url;
		} catch (Exception e) {
			LOG.error("上传图片失败！msg:{}", e);
		}
		return null;

	}

	@Override
	public List<BlueAdvert> findAdvert(int type) {
		try {
			List<BlueAdvert> adverts = advertDao.findList(type, null, 0,
					Integer.MAX_VALUE);
			return adverts;
		} catch (Exception e) {
			LOG.error("查询公告失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueStaff> findStaffByDeptId(Integer deptId) {
		try {
			List<BlueStaff> staffs = staffDao.findAllStaff(null,
					String.valueOf(deptId), 0, Integer.MAX_VALUE);
			return staffs;
		} catch (Exception e) {
			LOG.error("查询findStaffByDeptId失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueProject> findProjects() {
		try {
			List<BlueProject> projects = projectDao.findProjectList(null, 0,
					Integer.MAX_VALUE);
			return projects;
		} catch (Exception e) {
			LOG.error("查询findProjects失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueHospital> findHospitalByType(int type) {
		try {
			List<BlueHospital> hospital = hospitalDao.findBlueHospitals(null,
					type, 0, Integer.MAX_VALUE);
			return hospital;
		} catch (Exception e) {
			LOG.error("查询findHospitalByType失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueEncyclopedias> findEncyclopedias(int type) {
		try {
			List<BlueEncyclopedias> encyclopedias = encyclopediasDao.findList(
					type, null, 0, Integer.MAX_VALUE);
			return encyclopedias;
		} catch (Exception e) {
			LOG.error("查询findEncyclopedias失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueNeedWork> findBlueNeedWorks() {
		try {
			List<BlueNeedWork> works = workDao.findAllWork(null, 0,
					Integer.MAX_VALUE);
			return works;
		} catch (Exception e) {
			LOG.error("查询findBlueNeedWorks失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueStaff> findStaffBySearch(String search) {
		try {
			List<BlueStaff> staffs = staffDao.findLikeBySearch(search);
			return staffs;
		} catch (Exception e) {
			LOG.error("查询findStaffBySearch失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueShift> findBlueShift(Integer scheduleId) {
		try {
			List<BlueShift> shifts = shiftDao
					.findShiftsByscheduleId(scheduleId);
			return shifts;
		} catch (Exception e) {
			LOG.error("查询findBlueShift失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public BlueShift findShift(Integer sid) {
		try {
			BlueShift shifts = shiftDao.findShif(sid);
			return shifts;
		} catch (Exception e) {
			LOG.error("查询findShift失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BluePation> findPations(String userId) {
		try {
			List<BluePation> pations = pationDao.findPationsByuserId(userId);
			return pations;
		} catch (Exception e) {
			LOG.error("查询findPations失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public BluePation findPation(Integer sid) {
		try {
			BluePation pation = pationDao.findPationBysid(sid);
			return pation;
		} catch (Exception e) {
			LOG.error("查询findPations失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public BlueDeptDoctorPic findPic(Integer deptId) {
		try {
			BlueDeptDoctorPic pic = deptDoctorPicDao.findByDeptId(deptId);
			return pic;
		} catch (Exception e) {
			LOG.error("查询findPations失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public List<BlueStaff> findStaff(List<Integer> staffIds) {
		try {
			List<BlueStaff> staffs = staffDao.findBySids(staffIds);
			return staffs;
		} catch (Exception e) {
			LOG.error("获取所有医生失败！msg:{}", e);
			return null;
		}
	}

	@Override
	public List<BlueProjectAdvert> findTypeAdvert(int type) {
		try {
			List<BlueProjectAdvert> adverts = projectAdvertDao.findTypeAdvert(type);
			return adverts;
		} catch (Exception e) {
			LOG.error("获取findTypeAdvert失败！msg:{}", e);
			return null;
		}
	}


}

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
import com.common.system.dao.StaffDao;
import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueHospital;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueSource;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

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
	
	private Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.common.system.service.CommonService#findDept()
	 */
	@Override
	public List<BlueDept> findDept() {
		try {
			List<BlueDept> depts = deptDao.findDepts(0, Integer.MAX_VALUE);
			return depts;
		} catch (Exception e) {
			LOG.error("获取所有部门失败！msg:{}",e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.CommonService#findStaff()
	 */
	@Override
	public List<BlueStaff> findStaff() {
		try {
			List<BlueStaff> staffs = staffDao.findAllStaff(null,"-1",0, Integer.MAX_VALUE);
			return staffs;
		} catch (Exception e) {
			LOG.error("获取所有医生失败！msg:{}",e);
			return null;
		}
	}

	@Override
	public String upFile(MultipartFile file, int sid,Object object) {
		try {
			String url = PicUtil.upFile(file);
			if(object instanceof BlueAdvert){
				((BlueAdvert) object).setSid(sid);
				((BlueAdvert) object).setUrl(url);
				advertDao.updateUrl((BlueAdvert)object);
				
			}else if(object instanceof BlueSource){
				
			}else if(object instanceof BlueProject){
				
			}else if(object instanceof BlueHospital){
				
			}
			return url;
		} catch (Exception e) {
			LOG.error("上传图片失败！msg:{}",e);
		}
		return null;
		
	}

}

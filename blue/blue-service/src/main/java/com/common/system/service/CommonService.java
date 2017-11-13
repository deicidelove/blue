/**
 * 
 */
package com.common.system.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
import com.common.system.entity.BlueStaff;
import com.common.system.util.PageBean;

/**
 * @author amkong
 *
 */
public interface CommonService {
	
	public List<BlueDept> findDept();
	
	public List<BlueStaff> findStaff();
	
	public List<BlueStaff> findStaff(List<Integer> staffIds);
	
	public String upFile(MultipartFile file, int sid,Object object);
	
	public List<BlueAdvert> findAdvert(int type);
	
	public List<BlueEncyclopedias> findEncyclopedias(int type);
	
	public List<BlueStaff> findStaffByDeptId(Integer deptId);
	
	public List<BlueProject> findProjects();
	
	public List<BlueHospital> findHospitalByType(int type);
	
	public List<BlueNeedWork> findBlueNeedWorks();
	
	public List<BlueStaff> findStaffBySearch(String search);
	
	public List<BlueShift> findBlueShift(Integer scheduleId);
	
	public BlueShift findShift(Integer sid);
	
	public List<BluePation> findPations(String userId);
	
	public BluePation findPation(Integer sid);
	
	public BlueDeptDoctorPic findPic(Integer deptId);
	
	public List<BlueProjectAdvert> findTypeAdvert(int type);
	
	
	

	
}

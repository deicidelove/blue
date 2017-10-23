/**
 * 
 */
package com.common.system.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueEncyclopedias;
import com.common.system.entity.BlueHospital;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueStaff;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface CommonService {
	
	public List<BlueDept> findDept();
	
	public List<BlueStaff> findStaff();
	
	public String upFile(MultipartFile file, int sid,Object object);
	
	public List<BlueAdvert> findAdvert(int type);
	
	public List<BlueEncyclopedias> findEncyclopedias(int type);
	
	public List<BlueStaff> findStaffByDeptId(Integer deptId);
	
	public List<BlueProject> findProjects();
	
	public List<BlueHospital> findHospitalByType(int type);
	
}

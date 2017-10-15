/**
 * 
 */
package com.common.system.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueDept;
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
	
}

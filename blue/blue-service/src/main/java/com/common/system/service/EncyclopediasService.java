/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueEncyclopedias;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface EncyclopediasService {

	public PageBean<BlueEncyclopedias> getEncyclopediasList(int type,String date, int startPage,
			int limitLength);

	public Result<Integer> deleteEncyclopedias(int sid);

	public Result<Integer> addEncyclopedias(int type, String context, String title,
			MultipartFile file);

	public Result<BlueEncyclopedias> findEncyclopedias(int sid);

	public Result<Integer> updateEncyclopedias(int type, String context, String title,
			MultipartFile file, int sid);

}

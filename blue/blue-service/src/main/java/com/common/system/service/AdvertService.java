/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueAdvert;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface AdvertService {

	/**
	 * 获取广告列表
	 * 
	 * @param type
	 *            广告/通知
	 * @return
	 */
	public PageBean<BlueAdvert> getAdverList(int type,String date, int startPage,
			int limitLength);

	/**
	 * 删除某条广告
	 * 
	 * @return
	 */
	public Result<Integer> deleteAdver(int sid);

	/**
	 * 添加某条广告
	 * 
	 * @param type
	 *            广告/通知
	 * @param file
	 *            图片
	 * @param context
	 *            介绍文本
	 * @return
	 */
	public Result<Integer> addAdver(int type, String context, String title,
			MultipartFile file);

	/**
	 * 查询某条广告
	 * 
	 * @param sid
	 * @return
	 */
	public Result<BlueAdvert> findAdvert(int sid);

	/**
	 * 更新广告、通知内容
	 * 
	 * @param type
	 * @param context
	 * @param title
	 * @param file
	 * @return
	 */
	public Result<Integer> updateAdvert(int type, String context, String title,
			MultipartFile file, int sid);

}

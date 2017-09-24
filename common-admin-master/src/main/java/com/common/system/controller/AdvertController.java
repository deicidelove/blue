/**
 * 
 */
package com.common.system.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueAdvert;
import com.common.system.service.AdvertService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("/advert")
public class AdvertController {

	@Resource
	private AdvertService advertService;

	/**
	 * 获取广告列表
	 * 
	 * @param type
	 *            广告/通知
	 * @return
	 */
	@RequestMapping("/list")
	public PageBean<BlueAdvert> getAdverList(int type, int startPage,
			int limitLength) {
		return advertService.getAdverList(type, startPage, limitLength);
	}

	/**
	 * 删除某条广告
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	public Result<String> deleteAdver(int sid) {
		return advertService.deleteAdver(sid);
	}

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
	@RequestMapping("/add")
	public Result<String> addAdver(int type, String context, String title,
			MultipartFile file) {
		return advertService.addAdver(type, context, title, file);
	}

	/**
	 * 更新广告、通知
	 * 
	 * @param type
	 * @param context
	 * @param title
	 * @param file
	 * @return
	 */
	@RequestMapping("/updateAdvert")
	public Result<String> updateAdvert(int type, String context, String title,
			MultipartFile file, int sid) {
		return advertService.updateAdvert(type, context, title, file, sid);
	}

}

/**
 * 
 */
package com.common.system.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.AdvertDao;
import com.common.system.entity.BlueAdvert;
import com.common.system.service.AdvertService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("advertService")
public class AdvertServiceImpl implements AdvertService {

	private Logger LOG = LoggerFactory.getLogger(AdvertService.class);

	@Resource
	private AdvertDao advertDao;

	@Override
	public PageBean<BlueAdvert> getAdverList(int type, int startPage,
			int limitLength) {
		try {
			int count = advertDao.findNum(type);
			List<BlueAdvert> advertList = advertDao.findList(type,
					((startPage - 1) * limitLength), limitLength);
			PageBean<BlueAdvert> pageList = new PageBean<BlueAdvert>();
			pageList.setiDisplayStart(startPage);
			pageList.setiDisplayLength(limitLength);
			pageList.setData(advertList);
			pageList.setiTotalDisplayRecords(count);
			return pageList;
		} catch (Exception e) {
			LOG.error("获取广告/通知列表失败！type:{},msg:{}", type, e);
			return null;
		}
	}

	@Override
	public Result<String> deleteAdver(int sid) {
		try {
			int count = advertDao.deleteNum(sid);
			if (count > 0) {
				return new Result<>(false, "删除成功！", null);
			} else {
				return new Result<>(false, "未删除任何数据，请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除广告/通知失败！msg:{},sid:{}", e, sid);
			return new Result<>(false, "删除失败，请刷新后操作！", null);
		}

	}

	@Override
	public Result<String> addAdver(int type, String context, String title,
			MultipartFile file) {
		try {
			String url = "";
			if (file != null && file.getName() != null && !file.isEmpty()) {
				byte[] bytes = file.getBytes();
				// 字节流操作，写入文件等等（有图片就保存图片）

			}
			BlueAdvert advert = new BlueAdvert();
			advert.setType(type);
			advert.setContext(context);
			advert.setUrl(url);
			advert.setTitle(title);
			advert.setCreateTime(new Date());

			advertDao.addAdvert(advert);
			return new Result<String>(true, "保存成功！", null);
		} catch (IOException e) {
			LOG.error("保存广告、通知失败！msg:{},type:{}", e, type);
			return new Result<String>(false, "保存失败！！", null);
		}

	}

	@Override
	public Result<BlueAdvert> findAdvert(int sid) {
		try {
			Assert.isNull(sid, "sid is not be null");
			BlueAdvert advert = advertDao.findAdvert(sid);
			return new Result<BlueAdvert>(true, "查询成功！", advert);
		} catch (Exception e) {
			LOG.error("查询失败！sid:{},msg:{}", sid, e);
			return new Result<BlueAdvert>(false, "查询失败！", null);
		}
	}

	@Override
	public Result<String> updateAdvert(int type, String context, String title,
			MultipartFile file, int sid) {
		try {
			String url = "";
			if (file != null && file.getName() != null && !file.isEmpty()) {
				byte[] bytes = file.getBytes();
				// 字节流操作，写入文件等等（有图片就保存图片）

			}
			BlueAdvert advert = new BlueAdvert();
			advert.setSid(sid);
			advert.setType(type);
			advert.setContext(context);
			advert.setTitle(title);
			advert.setUrl(url);

			advertDao.updateAdvert(advert);
			return new Result<String>(true, "更新成功！", null);
		} catch (Exception e) {
			LOG.error("更新广告、通知失败！msg:{},sid:{}", e, sid);
			return new Result<String>(false, "更新失败！", null);
		}

	}

}

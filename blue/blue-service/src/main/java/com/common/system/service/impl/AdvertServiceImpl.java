/**
 * 
 */
package com.common.system.service.impl;

import java.io.File;
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
import com.common.system.util.PicUtil;
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
	public PageBean<BlueAdvert> getAdverList(int type,String date, int startPage,
			int limitLength) {
		try {
			int count = advertDao.findNum(type,date);
			List<BlueAdvert> advertList = advertDao.findList(type,date,
					startPage, limitLength);
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
	public Result<Integer> deleteAdver(int sid) {
		try {
			int count = advertDao.deleteNum(sid);
			return new Result<>(false, "删除成功！", count);
		} catch (Exception e) {
			LOG.error("删除广告/通知失败！msg:{},sid:{}", e, sid);
			return new Result<>(false, "删除失败，请刷新后操作！", null);
		}

	}

	@Override
	public Result<Integer> addAdver(int type, String context, String title,
			MultipartFile file) {
		try {
			String urlTream =PicUtil.upFile(file);
			BlueAdvert advert = new BlueAdvert();
			advert.setType(type);
			advert.setContext(context);
			advert.setUrl(urlTream);
			advert.setTitle(title);
			advert.setCreateTime(new Date());
			int count = advertDao.addAdvert(advert);
			return new Result<Integer>(true, "保存成功！", count);
		} catch (Exception e) {
			LOG.error("保存广告、通知失败！msg:{},type:{}", e, type);
			return new Result<Integer>(false, "保存失败！！", null);
		}

	}

	@Override
	public Result<BlueAdvert> findAdvert(int sid) {
		try {
			BlueAdvert advert = advertDao.findAdvert(sid);
			return new Result<BlueAdvert>(true, "查询成功！", advert);
		} catch (Exception e) {
			LOG.error("查询失败！sid:{},msg:{}", sid, e);
			return new Result<BlueAdvert>(false, "查询失败！", null);
		}
	}

	@Override
	public Result<Integer> updateAdvert(int type, String context, String title,
			MultipartFile file, int sid) {
		try {
            String urlTream = PicUtil.upFile(file);
			BlueAdvert advert = new BlueAdvert();
			advert.setSid(sid);
			advert.setType(type);
			advert.setContext(context);
			advert.setTitle(title);
			advert.setUrl(urlTream);

			int count =advertDao.updateAdvert(advert);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("更新广告、通知失败！msg:{},sid:{}", e, sid);
			return new Result<Integer>(false, "更新失败！", null);
		}

	}

}

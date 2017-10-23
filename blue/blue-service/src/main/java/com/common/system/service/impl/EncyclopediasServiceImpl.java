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
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.EncyclopediasDao;
import com.common.system.entity.BlueEncyclopedias;
import com.common.system.service.AdvertService;
import com.common.system.service.EncyclopediasService;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("encyclopediasService")
public class EncyclopediasServiceImpl implements EncyclopediasService {

	private Logger LOG = LoggerFactory.getLogger(AdvertService.class);

	@Resource
	private EncyclopediasDao encyclopediasDao;

	@Override
	public PageBean<BlueEncyclopedias> getEncyclopediasList(int type,String date, int startPage,
			int limitLength) {
		try {
			int count = encyclopediasDao.findNum(type,date);
			List<BlueEncyclopedias> EncyclopediasList = encyclopediasDao.findList(type,date,
					startPage  * limitLength, limitLength);
			PageBean<BlueEncyclopedias> pageList = new PageBean<BlueEncyclopedias>();
			pageList.setiDisplayStart(startPage);
			pageList.setiDisplayLength(limitLength);
			pageList.setData(EncyclopediasList);
			pageList.setiTotalDisplayRecords(count);
			return pageList;
		} catch (Exception e) {
			LOG.error("获取getEncyclopediasList列表失败！type:{},msg:{}", type, e);
			return null;
		}
	}

	@Override
	public Result<Integer> deleteEncyclopedias(int sid) {
		try {
			int count =encyclopediasDao.deleteNum(sid);
			return new Result<>(false, "删除成功！", count);
		} catch (Exception e) {
			LOG.error("删除Encyclopedias失败！msg:{},sid:{}", e, sid);
			return new Result<>(false, "删除失败，请刷新后操作！", null);
		}

	}

	@Override
	public Result<Integer> addEncyclopedias(int type, String context, String title,
			MultipartFile file) {
		try {
			String urlTream =PicUtil.upFile(file);
			BlueEncyclopedias encyclopedias = new BlueEncyclopedias();
			encyclopedias.setType(type);
			encyclopedias.setContext(context);
			encyclopedias.setUrl(urlTream);
			encyclopedias.setTitle(title);
			encyclopedias.setCreateTime(new Date());
			int count = encyclopediasDao.addencyclopedias(encyclopedias);
			return new Result<Integer>(true, "保存成功！", count);
		} catch (Exception e) {
			LOG.error("保存addEncyclopedias失败！msg:{},type:{}", e, type);
			return new Result<Integer>(false, "保存失败！！", null);
		}

	}

	@Override
	public Result<BlueEncyclopedias> findEncyclopedias(int sid) {
		try {
			BlueEncyclopedias advert = encyclopediasDao.findencyclopedias(sid);
			return new Result<BlueEncyclopedias>(true, "查询成功！", advert);
		} catch (Exception e) {
			LOG.error("查询失败！sid:{},msg:{}", sid, e);
			return new Result<BlueEncyclopedias>(false, "查询失败！", null);
		}
	}

	@Override
	public Result<Integer> updateEncyclopedias(int type, String context, String title,
			MultipartFile file, int sid) {
		try {
            String urlTream = PicUtil.upFile(file);
			BlueEncyclopedias encyclopedias = new BlueEncyclopedias();
			encyclopedias.setSid(sid);
			encyclopedias.setType(type);
			encyclopedias.setContext(context);
			encyclopedias.setTitle(title);
			encyclopedias.setUrl(urlTream);

			int count =encyclopediasDao.updateencyclopedias(encyclopedias);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("更新updateEncyclopedias失败！msg:{},sid:{}", e, sid);
			return new Result<Integer>(false, "更新失败！", null);
		}

	}

}

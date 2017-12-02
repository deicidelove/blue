/**
 * 
 */
package com.common.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.ProjectAdvertDao;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueProjectAdvert;
import com.common.system.service.ProjectAdvertService;
import com.common.system.service.ProjectService;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("projectAdvertService")
public class ProjectAdvertServiceImpl implements ProjectAdvertService {
	private Logger LOG = LoggerFactory.getLogger(ProjectAdvertServiceImpl.class);
	
	@Resource
	private ProjectAdvertDao projectAdvertDao;

	/* (non-Javadoc)
	 * @see com.common.system.service.ProjectAdvertService#findProjectAdevertList(java.lang.String, int, int)
	 */
	@Override
	public PageBean<BlueProjectAdvert> findProjectAdevertList(String date,
			int startPage, int limitLength,int type) {
		try {
			int count = projectAdvertDao.findCount(date,type);
			List<BlueProjectAdvert> list = projectAdvertDao.findProjectList(date,startPage, limitLength,type);
			PageBean<BlueProjectAdvert> page = new PageBean<BlueProjectAdvert>();
			page.setiDisplayLength(limitLength);
			page.setiDisplayStart(startPage);
			page.setiTotalDisplayRecords(count);
			page.setData(list);
			return page;
		} catch (Exception e) {
			LOG.error("查询findProjectAdevertList失败！msg：{}", e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.ProjectAdvertService#deleteProjectAdevert(int)
	 */
	@Override
	public Result<Integer> deleteProjectAdevert(int sid) {
		try {
			int count = projectAdvertDao.deleteProjectAdvert(sid);
			if (count > 0) {
				return new Result<Integer>(true, "删除成功！", count);
			} else {
				return new Result<Integer>(false, "未删除任何数据，请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除deleteProjectAdevert失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "删除失败！", null);
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.ProjectAdvertService#updateProjectAdevert(java.lang.String, java.lang.String, int, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Result<Integer> updateProjectAdevert(String title, String context,String jumpUrl,
			int sid, MultipartFile file,int type) {
		try {
			String url = PicUtil.upFile(file);
			BlueProjectAdvert projectAdvert = new BlueProjectAdvert();
			projectAdvert.setType(type);
			projectAdvert.setTitle(title);
			projectAdvert.setContext(context);
			projectAdvert.setJumpUrl(jumpUrl);
			projectAdvert.setPicUrl(url);
			projectAdvert.setSid(sid);
			int count = projectAdvertDao.updateProjectAdvert(projectAdvert);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("项目更新失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "更新失败！", null);
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.ProjectAdvertService#addProjectAdevert(java.lang.String, java.lang.String, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Result<Integer> addProjectAdevert(String title, String context,String jumpUrl,
			 MultipartFile file,int type) {
		try {
			String url = PicUtil.upFile(file);
			BlueProjectAdvert projectAdvert = new BlueProjectAdvert();
			projectAdvert.setType(type);
			projectAdvert.setTitle(title);
			projectAdvert.setContext(context);
			projectAdvert.setJumpUrl(jumpUrl);
			projectAdvert.setPicUrl(url);
			projectAdvert.setCreateTime(new Date());
			int count = projectAdvertDao.addProjectAdvert(projectAdvert);
			return new Result<Integer>(true, "保存成功！", count);
		} catch (Exception e) {
			LOG.error("addProjectAdevert保存失败！msg:{}", e);
		}
		return new Result<Integer>(false, "保存失败！", null);
	}

	/* (non-Javadoc)
	 * @see com.common.system.service.ProjectAdvertService#findProjectAdevert(int)
	 */
	@Override
	public Result<BlueProjectAdvert> findProjectAdevert(int sid) {
		try {
			BlueProjectAdvert project = projectAdvertDao.findProjectAdvert(sid);
			return new Result<BlueProjectAdvert>(true, "查询成功！", project);
		} catch (Exception e) {
			LOG.error("查询findProjectAdevert详情失败！msg：{}，sid:{}", e, sid);
		}
		return new Result<BlueProjectAdvert>(false, "查询失败！", null);
	}

}

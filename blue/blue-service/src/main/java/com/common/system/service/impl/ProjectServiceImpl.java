/**
 * 
 */
package com.common.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.system.dao.ProjectDao;
import com.common.system.entity.BlueProject;
import com.common.system.service.ProjectService;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	private Logger LOG = LoggerFactory.getLogger(ProjectService.class);

	@Resource
	private ProjectDao projectDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.ProjectService#findProjectList(int, int)
	 */
	@Override
	public PageBean<BlueProject> findProjectList(String date,int startPage, int limitLength) {
		try {
			int count = projectDao.findCount(date);
			List<BlueProject> list = projectDao.findProjectList(date,startPage 
					* limitLength, limitLength);
			PageBean<BlueProject> page = new PageBean<>();
			page.setiDisplayLength(limitLength);
			page.setiDisplayStart(startPage);
			page.setiTotalDisplayRecords(count);
			page.setData(list);
			return page;
		} catch (Exception e) {
			LOG.error("查询项目失败！msg：{}", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.ProjectService#deleteProject(int)
	 */
	@Override
	public Result<Integer> deleteProject(int sid) {
		try {
			int count = projectDao.deleteProject(sid);
			if (count > 0) {
				return new Result<Integer>(true, "删除成功！", count);
			} else {
				return new Result<Integer>(false, "未删除任何数据，请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除项目失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "删除失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.common.system.service.ProjectService#updateProject(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public Result<Integer> updateProject(String name, String context, int sid,MultipartFile file) {
		try {
			String url = PicUtil.upFile(file);
			BlueProject project = new BlueProject();
			project.setUrl(url);
			project.setSid(sid);
			project.setName(name);
			project.setContext(context);
			int count = projectDao.updateProject(project);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("项目更新失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "更新失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.common.system.service.ProjectService#addProject(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Result<Integer> addProject(String name, String context,MultipartFile file) {
		try {
			String url = PicUtil.upFile(file);
			BlueProject project = new BlueProject();
			project.setUrl(url);
			project.setName(name);
			project.setContext(context);
			project.setCreateTime(new Date());
			int count = projectDao.addProject(project);
			return new Result<Integer>(true, "保存成功！", count);
		} catch (Exception e) {
			LOG.error("项目保存失败！msg:{}", e);
		}
		return new Result<Integer>(false, "保存失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.ProjectService#findProject(int)
	 */
	@Override
	public Result<BlueProject> findProject(int sid) {
		try {
			BlueProject project = projectDao.findProject(sid);
			return new Result<BlueProject>(true, "查询成功！", project);
		} catch (Exception e) {
			LOG.error("查询项目详情失败！msg：{}，sid:{}", e, sid);
		}
		return new Result<BlueProject>(false, "查询失败！", null);
	}

}

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

import com.common.system.dao.ProjectDao;
import com.common.system.entity.BlueProject;
import com.common.system.service.ProjectService;
import com.common.system.util.PageBean;
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
	public PageBean<BlueProject> findProjectList(int startPage, int limitLength) {
		try {
			int count = projectDao.findCount();
			List<BlueProject> list = projectDao.findProjectList((startPage - 1)
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
	public Result<String> deleteProject(int sid) {
		try {
			int count = projectDao.deleteProject(sid);
			if (count > 0) {
				return new Result<String>(true, "删除成功！", null);
			} else {
				return new Result<String>(false, "未删除任何数据，请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除项目失败！msg:{},sid:{}", e, sid);
		}
		return new Result<String>(false, "删除失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.common.system.service.ProjectService#updateProject(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public Result<String> updateProject(String name, String context, int sid) {
		try {
			BlueProject project = new BlueProject();
			project.setSid(sid);
			project.setName(name);
			project.setConetxt(context);
			projectDao.updateProject(project);
			return new Result<String>(true, "更新成功！", null);
		} catch (Exception e) {
			LOG.error("项目更新失败！msg:{},sid:{}", e, sid);
		}
		return new Result<String>(false, "更新失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.common.system.service.ProjectService#addProject(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Result<String> addProject(String name, String context) {
		try {
			BlueProject project = new BlueProject();
			project.setName(name);
			project.setConetxt(context);
			project.setCreateTime(new Date());
			projectDao.updateProject(project);
			return new Result<String>(true, "保存成功！", null);
		} catch (Exception e) {
			LOG.error("项目保存失败！msg:{}", e);
		}
		return new Result<String>(false, "保存失败！", null);
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

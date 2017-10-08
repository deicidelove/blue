/**
 * 
 */
package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.DeptDao;
import com.common.system.entity.RcDept;
import com.common.system.service.DeptService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

	private Logger LOG = LoggerFactory.getLogger(DeptServiceImpl.class);

	@Resource
	private DeptDao deptDao;

	@Override
	public PageBean<RcDept> findDepts(int start,int limit) {
		try {
			int count = deptDao.findCount();
			List<RcDept> depts = deptDao.findDepts(start*limit,limit);
			PageBean<RcDept> page = new PageBean<>();
			page.setData(depts);
			page.setiDisplayStart(start);
			page.setiDisplayLength(limit);
			page.setiTotalDisplayRecords(count);
			return page;
		} catch (Exception e) {
			LOG.error("获取科室列表失败！msg：{}", e);
		}
		return null;
	}

	@Override
	public Result<String> deleteDept(int sid) {
		try {
			int count = deptDao.deleteDept(sid);
			if (count > 0) {
				return new Result<String>(true, "删除成功！", null);
			} else {
				return new Result<String>(false, "未删除任何数据，请刷新后操作！", null);
			}

		} catch (Exception e) {
			LOG.error("删除科室失败！sid:{},msg:{}", sid, e);
			return new Result<String>(false, "删除失败！", null);
		}
	}

	@Override
	public Result<RcDept> findBySid(int sid) {
		try {
			RcDept dept = deptDao.findBySid(sid);
			return new Result<RcDept>(true, "查询成功！", dept);
		} catch (Exception e) {
			LOG.error("查询科室失败！msg:{}sid:{}", e, sid);
		}
		return new Result<RcDept>(false, "查询失败！", null);
	}

	@Override
	public Result<String> addDept(String name, String context) {
		try {
			RcDept dept = new RcDept();
			
			deptDao.addDept(dept);
			return new Result<String>(true, "添加成功！", null);

		} catch (Exception e) {
			LOG.error("添加科室失败！msg:{}", e);
		}
		return new Result<String>(false, "添加失败！", null);
	}

	@Override
	public Result<String> updateDept(String name, String context, int sid) {
		try {
			RcDept dept = new RcDept();
			deptDao.addDept(dept);
			return new Result<String>(true, "更新成功！", null);
		} catch (Exception e) {
			LOG.error("更新科室信息失败！msg:{},sid:{}", e, sid);
		}
		return new Result<String>(false, "更新失败！", null);
	}

}

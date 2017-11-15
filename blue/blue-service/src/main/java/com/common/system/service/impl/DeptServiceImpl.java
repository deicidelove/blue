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

import com.common.system.dao.DeptDao;
import com.common.system.entity.BlueDept;
import com.common.system.service.DeptService;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
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
	public PageBean<BlueDept> findDepts(int start,int limit) {
		try {
			int count = deptDao.findCount();
			List<BlueDept> depts = deptDao.findDepts(start,limit);
			PageBean<BlueDept> page = new PageBean<>();
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
	public Result<Integer> deleteDept(int sid) {
		try {
			int count = deptDao.deleteDept(sid);
			if (count > 0) {
				return new Result<Integer>(true, "删除成功！", count);
			} else {
				return new Result<Integer>(false, "未删除任何数据，请刷新后操作！", null);
			}

		} catch (Exception e) {
			LOG.error("删除科室失败！sid:{},msg:{}", sid, e);
			return new Result<Integer>(false, "删除失败！", null);
		}
	}

	@Override
	public Result<BlueDept> findBySid(int sid) {
		try {
			BlueDept dept = deptDao.findBySid(sid);
			return new Result<BlueDept>(true, "查询成功！", dept);
		} catch (Exception e) {
			LOG.error("查询科室失败！msg:{}sid:{}", e, sid);
		}
		return new Result<BlueDept>(false, "查询失败！", null);
	}

	@Override
	public Result<Integer> addDept(String name, String context,MultipartFile file) {
		try {
			String urlTream =PicUtil.upFile(file);
			BlueDept dept = new BlueDept();
			dept.setUrl(urlTream);
			dept.setContext(context);
			dept.setName(name);
			dept.setCreateTime(new Date());
			int count = deptDao.addDept(dept);
			return new Result<Integer>(true, "添加成功！", count);

		} catch (Exception e) {
			LOG.error("添加科室失败！msg:{}", e);
		}
		return new Result<Integer>(false, "添加失败！", null);
	}

	@Override
	public Result<Integer> updateDept(String deptName,String context,int sid,MultipartFile file) {
		try {
			String urlTream =PicUtil.upFile(file);
			BlueDept dept = new BlueDept();
			dept.setUrl(urlTream);
			dept.setContext(context);
			dept.setName(deptName);
			dept.setSid(sid);
			int count = deptDao.updateDept(dept);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("更新科室信息失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "更新失败！", null);
	}

}

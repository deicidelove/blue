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

import com.common.system.dao.SourceDao;
import com.common.system.dao.StaffDao;
import com.common.system.entity.BlueSource;
import com.common.system.entity.BlueStaff;
import com.common.system.service.SourceService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("sourceService")
public class SourceServiceImpl implements SourceService {

	private Logger LOG = LoggerFactory.getLogger(SourceServiceImpl.class);

	@Resource
	private SourceDao sourceDao;

	@Resource
	private StaffDao staffDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.SourceService#findSourceList(int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public PageBean<BlueSource> findSourceList(int deptId, String jobNum,
			String password, int startPage, int limit) {
		try {
			// 校验该用户权限
			BlueStaff staff = staffDao.findByJobNum(jobNum, password);
			if (staff != null) {
				int count = sourceDao.findSourceListCount(deptId,
						staff.getPositionId());
				List<BlueSource> list = sourceDao.findSourceList(deptId,
						staff.getPositionId(), (startPage - 1) * limit, limit);
				PageBean<BlueSource> pageList = new PageBean<BlueSource>();
				pageList.setiDisplayStart(startPage);
				pageList.setiDisplayLength(limit);
				pageList.setData(list);
				pageList.setiTotalDisplayRecords(count);
				return pageList;
			}
		} catch (Exception e) {
			LOG.error("获取广告/通知列表失败！jobNum:{},msg:{}", jobNum, e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.SourceService#findSourceByDept(int)
	 */
	@Override
	public PageBean<BlueSource> findSourceByDept(int deptId, int startPage,
			int limit) {
		try {
			int count = sourceDao.findSourceByDeptCount(deptId);
			List<BlueSource> list = sourceDao.findSourceByDept(deptId,
					startPage * limit, limit);
			PageBean<BlueSource> pageList = new PageBean<BlueSource>();
			pageList.setiDisplayStart(startPage);
			pageList.setiDisplayLength(limit);
			pageList.setData(list);
			pageList.setiTotalDisplayRecords(count);
			return pageList;
		} catch (Exception e) {
			LOG.error("获取广告/通知列表失败！deptId:{},msg:{}", deptId, e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.SourceService#deleteSource(int)
	 */
	@Override
	public Result<String> deleteSource(int sid) {
		try {
			int count = sourceDao.deleteSource(sid);
			if (count > 0) {
				return new Result<String>(true, "删除成功！", null);
			} else {
				return new Result<String>(false, "未删除任何数据,请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除资料失败！msg:{},sid:{}", e, sid);
			return new Result<String>(false, "删除失败！", null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.SourceService#addSource(int, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Result<String> addSource(int deptId, int positionId, String context,
			String title) {
		try {
			BlueSource source = new BlueSource();
			source.setDeptId(deptId);
			source.setPositionId(positionId);
			source.setTitle(title);
			source.setContext(context);
			source.setCreateTime(new Date());
			sourceDao.addSource(source);
			return new Result<String>(true, "保存成功！", null);

		} catch (Exception e) {
			LOG.error("保存资源出现异常！msg:{}", e);
		}
		return new Result<String>(false, "保存失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.SourceService#updateSource(int, int, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Result<String> updateSource(int sid, int deptId, int positionId,
			String context, String title) {
		try {
			BlueSource source = new BlueSource();
			source.setSid(sid);
			source.setDeptId(deptId);
			source.setPositionId(positionId);
			source.setTitle(title);
			source.setContext(context);
			sourceDao.updateSource(source);
			return new Result<String>(true, "更新成功！", null);
		} catch (Exception e) {
			LOG.error("更新资源出现异常！sid:{},msg:{}", sid, e);
		}
		return new Result<String>(false, "更新失败！", null);
	}

	@Override
	public Result<BlueSource> findSource(int sid) {
		try {
			BlueSource source = sourceDao.findSource(sid);
			return new Result<BlueSource>(true, "查询成功！", source);
		} catch (Exception e) {
			LOG.error("查询资料失败！msg:{},sid:{}", e, sid);
		}
		return new Result<BlueSource>(false, "查询失败！", null);
	}

}

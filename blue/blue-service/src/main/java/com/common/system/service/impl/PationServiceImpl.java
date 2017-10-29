/**
 * 
 */
package com.common.system.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.DeptDao;
import com.common.system.dao.PationDao;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BluePation;
import com.common.system.entity.BlueProject;
import com.common.system.service.PationService;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("pationService")
public class PationServiceImpl implements PationService {
	
	private Logger LOG = LoggerFactory.getLogger(PationServiceImpl.class);

	@Resource
	private PationDao pationDao;

	/* (non-Javadoc)
	 * @see com.common.system.service.PationService#updatePationIsDefault(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Result<Integer> updatePationIsDefault(Integer sid, Integer isDefault) {
		try{
			int count = pationDao.updateIsDefault(sid, isDefault);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("更新updatePationIsDefault失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "更新失败！", null);
	}

	@Override
	public Result<Integer> deletePation(int sid) {
		try {
			int count = pationDao.deletePation(sid);
			if (count > 0) {
				return new Result<Integer>(true, "删除成功！", count);
			} else {
				return new Result<Integer>(false, "未删除任何数据，请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除deletePation失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "删除失败！", null);
	}

	@Override
	public Result<Integer> updatePation(String name, String phone, int sid,
			Integer isDefault) {
		try {
			BluePation pation = new BluePation();
			pation.setSid(sid);
			pation.setName(name);
			pation.setPhone(phone);
			pation.setIsDefault(isDefault);
			pation.setCreateTime(new Date());
			int count = pationDao.updateProject(pation);
			return new Result<Integer>(true, "更新成功！", count);
		} catch (Exception e) {
			LOG.error("updatePation更新失败！msg:{},sid:{}", e, sid);
		}
		return new Result<Integer>(false, "更新失败！", null);
	}

	@Override
	public Result<Integer> addPation(String name, String phone
			Integer isDefault, Integer userId) {
		try {
			BluePation pation = new BluePation();
			pation.setName(name);
			pation.setPhone(phone);
			pation.setIsDefault(isDefault);
			pation.setUserId(userId);
			pation.setCreateTime(new Date());
			int count = pationDao.addProject(pation);
			return new Result<Integer>(true, "保存成功！", count);
		} catch (Exception e) {
			LOG.error("项目保存失败！msg:{}", e);
		}
		return new Result<Integer>(false, "保存失败！", null);
	}

}

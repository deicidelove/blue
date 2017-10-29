/**
 * 
 */
package com.common.system.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.WorkDao;
import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueNeedWork;
import com.common.system.entity.BlueWantWork;
import com.common.system.service.AdvertService;
import com.common.system.service.NeedWorkService;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Service("needWorkService")
public class NeedWorkServiceImpl implements NeedWorkService {

	private Logger LOG = LoggerFactory.getLogger(NeedWorkServiceImpl.class);
	
	@Resource
	private WorkDao workDao;
	/* (non-Javadoc)
	 * @see com.common.system.service.NeedWorkService#findBySid(int)
	 */
	@Override
	public Result<BlueNeedWork> findBySid(int sid) {
		try {
			BlueNeedWork work = workDao.findBySid(sid);
			return new Result<BlueNeedWork>(true, "查询成功！", work);
		} catch (Exception e) {
			LOG.error("查询失败！sid:{},msg:{}", sid, e);
			return new Result<BlueNeedWork>(false, "查询失败！", null);
		}
	}
	@Override
	public Result<Integer> saveAddWork(String addName, String phone,
			String educattion, String wantJob, String wageWant, String describe) {
		try {
			BlueWantWork wantWork = new BlueWantWork();
			wantWork.setName(addName);
			wantWork.setPhone(phone);
			wantWork.setProfession(educattion);
			wantWork.setWantJob(wantJob);
			wantWork.setWantWage(wageWant);
			wantWork.setDescribe(describe);
			wantWork.setCreateTime(new Date());
			int count = workDao.addWantWork(wantWork);
			return new Result<Integer>(true, "保存成功！", count);
		} catch (Exception e) {
			LOG.error("保存saveAddWork失败！msg:{}", e);
			return new Result<Integer>(false, "保存失败！！", null);
		}
	}

}

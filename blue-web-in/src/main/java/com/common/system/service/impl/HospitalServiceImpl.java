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

import com.common.system.dao.HospitalDao;
import com.common.system.entity.BlueHospital;
import com.common.system.service.HospitalService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {

	private Logger LOG = LoggerFactory.getLogger(HospitalServiceImpl.class);

	@Resource
	private HospitalDao hospitalDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.HospitalService#findBlueHospitals(int,
	 * int, int)
	 */
	@Override
	public PageBean<BlueHospital> findBlueHospitals(int type, int startPage,
			int limitLength) {
		try {
			int count = hospitalDao.findCount(type);
			List<BlueHospital> list = hospitalDao.findBlueHospitals(type,
					startPage * limitLength, limitLength);
			PageBean<BlueHospital> pageList = new PageBean<>();
			pageList.setiDisplayStart(startPage);
			pageList.setiDisplayLength(limitLength);
			pageList.setData(list);
			pageList.setiTotalDisplayRecords(count);
			return pageList;

		} catch (Exception e) {
			LOG.error("查询失败！msg:{},type:{}", e, type);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.HospitalService#deleteHospital(int)
	 */
	@Override
	public Result<String> deleteHospital(int sid) {
		try {
			int count = hospitalDao.deleteHospital(sid);
			if (count > 0) {
				return new Result<String>(true, "删除成功！", null);
			} else {
				return new Result<String>(false, "未删除任何数据,请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除医院信息失败！msg:{},sid:{}", e, sid);
			return new Result<String>(false, "删除失败！", null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.HospitalService#findHospital(int)
	 */
	@Override
	public Result<BlueHospital> findHospital(int sid) {
		try {
			BlueHospital hospital = hospitalDao.findHospital(sid);
			return new Result<BlueHospital>(true, "查询成功！", hospital);
		} catch (Exception e) {
			LOG.error("查询医院信息失败！msg:{},sid:{}", e, sid);
		}
		return new Result<BlueHospital>(true, "查询失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.system.service.HospitalService#updateHospital(int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Result<String> updateHospital(int type, int sid, String context,
			String title) {
		try {
			BlueHospital hospital = new BlueHospital();
			hospital.setSid(sid);
			hospital.setContext(context);
			hospital.setTitle(title);
			hospital.setType(type);
			hospitalDao.updateHospital(hospital);
			return new Result<String>(true, "保存成功！", null);
		} catch (Exception e) {
			LOG.error("保存医院信息失败msg:{}", e);
		}
		return new Result<String>(false, "保存失败！", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.common.system.service.HospitalService#addHospital(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Result<String> addHospital(int type, String context, String title) {
		try {
			BlueHospital hospital = new BlueHospital();
			hospital.setContext(context);
			hospital.setTitle(title);
			hospital.setCreateTime(new Date());
			hospital.setType(type);
			hospitalDao.addHospital(hospital);
			return new Result<String>(true, "保存成功！", null);
		} catch (Exception e) {
			LOG.error("保存医院信息失败msg:{}", e);
		}
		return new Result<String>(false, "保存失败！", null);
	}

}

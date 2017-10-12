/**
 * 
 */
package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;




import com.alibaba.fastjson.JSON;
import com.common.system.dao.StaffDao;
import com.common.system.entity.BlueStaff;
import com.common.system.service.StaffService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

	private Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Resource
	private StaffDao staffDao;

	@Override
	public PageBean<BlueStaff> findStaff(int startPage, int limitLength,
			String name, int deptId, String diseaseNmae) {
		try {
			name = "%" + name + "%";
			diseaseNmae = "%" + diseaseNmae + "%";
			int count = staffDao.findCount(name, deptId, diseaseNmae);
			List<BlueStaff> list = staffDao.findStaff((startPage - 1)
					* limitLength, limitLength, name, deptId, diseaseNmae);
			PageBean<BlueStaff> page = new PageBean<BlueStaff>();
			page.setData(list);
			page.setiDisplayLength(limitLength);
			page.setiDisplayStart(startPage);
			page.setiTotalDisplayRecords(count);
			return page;
		} catch (Exception e) {
			LOG.error("查询专家失败！msg:{}", e);
		}
		return null;
	}

	@Override
	public Result<BlueStaff> findBySid(int sid) {
		try {
			BlueStaff staff = staffDao.findBySid(sid);
			return new Result<BlueStaff>(true, "查询成功！", staff);
		} catch (Exception e) {
			LOG.error("查询医生失败！msg:{},sid:{}", e, sid);
		}
		return new Result<BlueStaff>(false, "查询失败！", null);
	}

	@Override
	public Result<String> deleteStaff(int sid) {
		try {
			int count = staffDao.deleteStaff(sid);
			if (count > 0) {
				return new Result<String>(true, "删除成功！", null);
			} else {
				return new Result<String>(false, "未删除任何数据,请刷新后操作！", null);
			}
		} catch (Exception e) {
			LOG.error("删除医生失败！msg:{},sid:{}", e, sid);
			return new Result<String>(false, "删除失败！", null);
		}
	}

	@Override
	public Result<String> updateStaff(String info) {
		try {
			if(StringUtils.isEmpty(info)){
				return new Result<String>(false, "无信息更新", null);
			}
			BlueStaff staff = JSON.parseObject(info, BlueStaff.class);
			staffDao.updateStaff(staff);
			return new Result<String>(true, "更新成功！", null);
		} catch (Exception e) {
			LOG.error("更新医生员工信息失败！msg:{}info:{}", e,info);
		}
		return new Result<String>(false, "更新失败！", null);
	}

	@Override
	public Result<String> addStaff(String info) {
		try {
			if(StringUtils.isEmpty(info)){
				return new Result<String>(false, "无信息保存", null);
			}
			BlueStaff staff = JSON.parseObject(info, BlueStaff.class);
			staffDao.updateStaff(staff);
			return new Result<String>(true, "保存成功！", null);
		} catch (Exception e) {
			LOG.error("更新医生员工信息失败！msg:{}info:{}", e,info);
		}
		return new Result<String>(false, "保存失败！", null);
	}

}

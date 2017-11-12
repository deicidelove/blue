package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.OrderDao;
import com.common.system.entity.OrderEntity;
import com.common.system.service.OrderService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Override
	public OrderEntity findByOrderId(Integer orderId) {
		OrderEntity orderEntity = null;
		try {
			orderEntity = orderDao.seleteById(orderId);
		} catch (Exception e) {
			LOG.error("  findByOrderId error!", e );
		}
		return orderEntity;
	}

	@Override
	public OrderEntity findByOutTradeId(String outTradeId) {
		OrderEntity orderEntity = null;
		try {
			orderEntity = orderDao.seleteByOutTradeId(outTradeId);
		} catch (Exception e) {
			LOG.error("  findByOrderId error!", e );
		}
		return orderEntity;
	}
	
	@Override
	public List<OrderEntity> seleteByList(String status, Integer pageNum,
			Integer pageSize) {
		List<OrderEntity> resultList = null;
		try {
			resultList = orderDao.seleteByList(status,null , pageNum, pageSize);
		} catch (Exception e) {
			LOG.error("  seleteByList error!", e );
		}
		return resultList;
	}

	@Override
	public PageInfo<OrderEntity> listForPage(String status, Integer pageNum,
			Integer pageSize) {
		PageInfo<OrderEntity> result = new PageInfo<OrderEntity>();
		try {
			List<OrderEntity> resultList = orderDao.seleteByList(status,null, pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error(" listForPage error!", e );
		}
		return result;
	}

	@Override
	public Integer save(OrderEntity orderEntity) {
		Integer resultInt = null;
		try {
			resultInt = orderDao.save(orderEntity);
		} catch (Exception e) {
			LOG.error(" save error!", e );
		}
		return resultInt;
	}

	@Override
	public void update(Integer orderId, String status, String prePayId) {
		try {
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setOrderId(orderId);
			orderEntity.setStatus(status);
			orderEntity.setPrePayId(prePayId);
			orderDao.update(orderEntity);
		} catch (Exception e) {
			LOG.error(" update error!", e );
		}

	}

	@Override
	public Result<Integer> delete(Integer orderId) {
		Result<Integer> result = new Result<Integer>();
		try {
			orderDao.deleteById(orderId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error(" delete error!", e );
		}
		return result;
	}

	@Override
	public PageInfo<OrderEntity> seleteListByOpenId(String openId, Integer pageNum,
			Integer pageSize) {
		PageInfo<OrderEntity> result = new PageInfo<OrderEntity>();
		try {
			List<OrderEntity> resultList = orderDao.seleteByList(null, openId, pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error(" seleteListByOpenId error!", e );
		}
		return result;
	}

}

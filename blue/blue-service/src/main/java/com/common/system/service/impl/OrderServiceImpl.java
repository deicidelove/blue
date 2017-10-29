package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.OrderDao;
import com.common.system.entity.OrderEntity;
import com.common.system.service.OrderService;
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
	public List<OrderEntity> seleteByList(String status, Integer pageNum,
			Integer pageSize) {
		List<OrderEntity> resultList = null;
		try {
			resultList = orderDao.seleteByList(status, pageNum, pageSize);
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
			List<OrderEntity> resultList = orderDao.seleteByList(status, pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error(" listForPage error!", e );
		}
		return result;
	}

	@Override
	public void save(OrderEntity orderEntity) {
		try {
			orderDao.save(orderEntity);
		} catch (Exception e) {
			LOG.error(" save error!", e );
		}
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
	public void delete(Integer orderId) {
		
		try {
			orderDao.deleteById(orderId);
		} catch (Exception e) {
			LOG.error(" delete error!", e );
		}
	}

}

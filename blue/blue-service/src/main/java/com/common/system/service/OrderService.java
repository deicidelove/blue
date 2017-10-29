package com.common.system.service;


import java.util.List;

import com.common.system.entity.OrderEntity;
import com.github.pagehelper.PageInfo;


public interface OrderService {
	
	OrderEntity findByOrderId(Integer orderId);
	
	List<OrderEntity> seleteByList(String status, Integer pageNum,
			Integer pageSize);
	
	PageInfo<OrderEntity> listForPage(String status, Integer pageNum, Integer pageSize);
	
	void save(OrderEntity orderEntity);
	
	void update(Integer orderId, String status, String prePayId);
	
	void delete(Integer orderId);
}

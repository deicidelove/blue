package com.common.system.service;


import java.util.List;

import com.common.system.entity.OrderEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;


public interface OrderService {
	
	OrderEntity findByOrderId(Integer orderId);
	
	OrderEntity findByOutTradeId(String outTradeId);
	
	List<OrderEntity> seleteByList(String status, Integer pageNum,
			Integer pageSize);
	
	PageInfo<OrderEntity> seleteListByOpenId(String openId, Integer pageNum,
			Integer pageSize);
	
	PageInfo<OrderEntity> listForPage(String status, Integer pageNum, Integer pageSize);
	
	Integer save(OrderEntity orderEntity);
	
	void update(Integer orderId, String status, String prePayId);
	
	Result<Integer> delete(Integer orderId);
}

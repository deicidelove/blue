package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.common.system.entity.OrderEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	图片表增刪改查
 */
@Repository("orderDao")
public class OrderDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public OrderEntity seleteById(Integer orderId){
		Assert.notNull(orderId,"orderId is null");
		String sql = " SELECT * FROM `rc_a_order`  WHERE 1=1 and is_delete is false and order_id = :goodsId  ";
		Map<String, Object> paramMap = Maps.newHashMap();
		
		OrderEntity result = namedParameterJdbcTemplate
				.queryForObject(sql, paramMap, new BeanPropertyRowMapper<OrderEntity>(OrderEntity.class));
				
		return result;
	}
	
	public List<OrderEntity> seleteByList(String status, String openId, Integer pageNum,
			Integer pageSize){
		String sql = " SELECT * FROM rc_a_order WHERE 1=1 and is_delete is false ";
		Map<String, Object> paramMap = Maps.newHashMap();

		if(StringUtils.isNotEmpty(status)){
			paramMap.put("status", status);
			sql += " and status = :status ";
		}
		
		if(StringUtils.isNotEmpty(openId)){
			paramMap.put("openId", openId);
			sql += " and openId = :openId ";
		}
		
		if(null != pageNum && null != pageSize){
			paramMap.put("pageStartNum", (pageNum-1)*pageSize);
			paramMap.put("pageSize", pageSize);
			sql += " limit :pageStartNum, :pageSize ";
		}
		
		sql += "  order by create_time desc ";
		List<OrderEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(OrderEntity.class));
		return resultList;
	}
	
	public List<OrderEntity> seleteByList(){
		String sql = " SELECT * FROM rc_a_order WHERE 1=1 and is_delete is false ";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<OrderEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(OrderEntity.class));
		return resultList;
	}
	
	public void save(OrderEntity orderEntity){
		Assert.notNull(orderEntity,"orderEntity is null");
		String sql ="	INSERT INTO rc_a_order	"
				+"	(goods_id, goods_num, `type`, `source`, status, open_id, pre_pay_id, out_trade_id, price, jifen)	"
				+ "	VALUES ( :goodsId, :goodsNum, :type, :source, :status, :openId, :prePayId, :outTradeId, :price,:jifen )	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(orderEntity));
	}
	
	/**
	 * 刪除
	 * @param goodsId
	 */
	public void deleteById(Integer orderId){
		Assert.notNull(orderId,"orderId is null");
		String sql = "update  `rc_a_order` set is_delete = 1 WHERE `order_id`=:orderId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("orderId", orderId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(OrderEntity orderEntity){
		Assert.notNull(orderEntity,"orderEntity is null");
		String sql = "	UPDATE `rc_a_order`  "
				+ "	SET  `pre_pay_id`=:prePayId, status = :status	"
				+ " WHERE `order_id`=:orderId" ;
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("prePayId", orderEntity.getPrePayId());
		paramMap.put("status", orderEntity.getStatus());
		paramMap.put("orderId", orderEntity.getOrderId());
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}

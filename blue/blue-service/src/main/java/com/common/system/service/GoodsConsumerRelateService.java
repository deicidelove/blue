package com.common.system.service;

import java.util.List;

import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.github.pagehelper.PageInfo;

public interface GoodsConsumerRelateService {
	
	public void saveGoodsConsumerRelate(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	public void deleteById(Integer goodsConsumerId);
	
	public void update(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	/**
	 * <p>
	 * <code>updateConsumer</code>
	 * </p>
	 * 用户占用中奖号码
	 * @author admin
	 * @param goodsConsumerRelateEntity
	 */
	public int updateConsumer(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	public GoodsConsumerRelateEntity getById(Integer goodsConsumerId);
	/**
	 * <p>
	 * <code>randomByGoodsId</code>
	 * </p>
	 * 随机获取一个中奖号码
	 * @author admin
	 * @param goodsId
	 * @return
	 */
	public GoodsConsumerRelateEntity randomByGoodsId(Integer goodsId);
	
	public PageInfo<GoodsConsumerRelateDTO> listForPage(Integer pageNum, Integer pageSize);
	
	public List<GoodsConsumerRelateEntity> list(Integer actId, Integer goodsId);

	public List<GoodsConsumerRelateEntity> listUsed(Integer actId, Integer goodsId);
	
}

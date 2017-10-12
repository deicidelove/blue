package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.common.system.dao.ActDao;
import com.common.system.dao.ConsumerDao;
import com.common.system.dao.GoodsConsumerRelateDao;
import com.common.system.dao.GoodsDao;
import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.ActEntity;
import com.common.system.entity.ConsumerEntity;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.service.GoodsConsumerRelateService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("goodsConsumerRelateService")
public class GoodsConsumerRelateServiceImpl implements GoodsConsumerRelateService {

	@Resource
	private GoodsConsumerRelateDao goodsConsumerRelateDao;
	
	@Resource
	private ActDao actDao;
	
	@Resource
	private ConsumerDao consumerDao;
	
	@Resource
	private GoodsDao goodsDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(GoodsConsumerRelateServiceImpl.class);
	
	@Override
	public void saveGoodsConsumerRelate(GoodsConsumerRelateEntity goodsConsumerRelateEntity) {
		
		try {
			goodsConsumerRelateDao.saveGoodsConsumerRelate(goodsConsumerRelateEntity);
			
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl saveAct error!", e );
		}
	}

	@Override
	public void deleteById(Integer goodsUserId) {
		
		try {
			goodsConsumerRelateDao.deleteById(goodsUserId);
			
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl deleteById error!", e );
		}
	}

	@Override
	public void update(GoodsConsumerRelateEntity goodsConsumerRelateEntity) {
		try {
			goodsConsumerRelateDao.update(goodsConsumerRelateEntity);
			
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl update error!", e );
		}

	}

	@Override
	public GoodsConsumerRelateEntity getById(Integer actId) {
		GoodsConsumerRelateEntity goodsConsumerRelateEntity = null;
		try {
			goodsConsumerRelateEntity = goodsConsumerRelateDao.seleteById(actId);
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl getById error!", e );
		}
		return goodsConsumerRelateEntity;
	}

	@Override
	public PageInfo<GoodsConsumerRelateDTO> listForPage(Integer pageNum,
			Integer pageSize) {
		PageInfo<GoodsConsumerRelateDTO> result = new PageInfo<GoodsConsumerRelateDTO>();
		try {
			List<GoodsConsumerRelateEntity> tempList = goodsConsumerRelateDao.seleteList(pageNum, pageSize);
			if(CollectionUtils.isEmpty(tempList)){
				return result;
			}
			List<GoodsConsumerRelateDTO> resultList = Lists.newArrayListWithExpectedSize(tempList.size());
			for(GoodsConsumerRelateEntity temp : tempList){
				GoodsConsumerRelateDTO relateDTO = new GoodsConsumerRelateDTO();
				BeanUtils.copyProperties(temp, relateDTO);
				ActEntity actEntity = actDao.seleteById(temp.getActId());
				relateDTO.setActName(actEntity.getActName());
				relateDTO.setActPeriods(actEntity.getActPeriods());
				GoodsEntity goodsEntity = goodsDao.seleteById(temp.getGoodsId());
				relateDTO.setGoodsName(goodsEntity.getGoodsName());
				ConsumerEntity consumerEntity = consumerDao.seleteById(temp.getConsumerId());
				relateDTO.setConsumerName(consumerEntity.getConsumerName());
				resultList.add(relateDTO);
			}
			
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("ActServiceImpl deleteById error!", e );
		}
		return result;
	}

}

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
	public void deleteById(Integer goodsConsumerId) {
		
		try {
			goodsConsumerRelateDao.deleteById(goodsConsumerId);
			
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
	public GoodsConsumerRelateEntity getById(Integer goodsConsumerId) {
		GoodsConsumerRelateEntity goodsConsumerRelateEntity = null;
		try {
			goodsConsumerRelateEntity = goodsConsumerRelateDao.seleteById(goodsConsumerId);
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
			LOG.error("GoodsUserRelateServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public List<GoodsConsumerRelateEntity> list(Integer actId, Integer goodsId) {
		List<GoodsConsumerRelateEntity>  resultList = Lists.newArrayList();
		try {
			resultList = goodsConsumerRelateDao.seleteListByActIdAndGoodsId(actId, goodsId);
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl list error!", e );
		}
		return resultList;
	}

	@Override
	public GoodsConsumerRelateEntity randomByGoodsId(Integer goodsId) {
		GoodsConsumerRelateEntity  result =null;
		try {
			result = goodsConsumerRelateDao.randomById(goodsId);
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl randomByGoodsId error!", e );
		}
		return result;
	}

	@Override
	public int updateConsumer(
			GoodsConsumerRelateEntity goodsConsumerRelateEntity) {
		int result = 0;
		try {
			result = goodsConsumerRelateDao.updateConsumer(goodsConsumerRelateEntity);
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl updateConsumer error!", e );
		}
		return result;
	}

	@Override
	public List<GoodsConsumerRelateEntity> listUsed(Integer actId, Integer goodsId) {
		List<GoodsConsumerRelateEntity>  resultList = Lists.newArrayList();
		try {
			resultList = goodsConsumerRelateDao.listUsed(actId, goodsId);
		} catch (Exception e) {
			LOG.error("GoodsUserRelateServiceImpl list error!", e );
		}
		return resultList;
	}

}

/**
 * 
 */
package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.common.system.entity.BlueAdvert;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("advertDao")
public class AdvertDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<BlueAdvert> findList(int type,String date, int startRow, int limit) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_advert WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (type != -1) {
			sql.append(" AND type =:type");
			paramMap.put("type", type);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limit);
		List<BlueAdvert> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueAdvert>(BlueAdvert.class));
		return result;
	}

	public int findNum(int type,String date) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_advert WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (type != -1) {
			sql.append(" AND type =:type");
			paramMap.put("type", type);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);
		return count;
	}

	public int deleteNum(int sid) {
		String sql = "DELETE FROM  tb_blue_advert  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addAdvert(BlueAdvert advert) {
		
		String sql = "INSERT INTO `tb_blue_advert` ( `type`, `url`, `title`, `context`, `create_time`) "
				+ "VALUES (:type, :url, :title, :context, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("type", advert.getType());
		paramMap.put("url", advert.getUrl()==null?"":advert.getUrl());
		paramMap.put("title", advert.getTitle()==null?"":advert.getTitle());
		paramMap.put("context", advert.getContext()==null?"":advert.getContext());
		paramMap.put("create_time", advert.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public BlueAdvert findAdvert(int sid) {
		String sql ="SELECT * FROM tb_blue_advert WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueAdvert> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueAdvert>(BlueAdvert.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}

	public int updateAdvert(BlueAdvert advert) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_advert` SET type =:type,title =:title,context =:context");
		Map<String, Object> paramMap = Maps.newHashMap();
		if(!StringUtils.isEmpty(advert.getUrl())){
			sql.append(",url =:url");
			paramMap.put("url", advert.getUrl());
		}
		sql.append(" WHERE sid =:sid");
		paramMap.put("sid", advert.getSid());
		paramMap.put("type", advert.getType());
		paramMap.put("title", advert.getTitle()==null?"":advert.getTitle());
		paramMap.put("context", advert.getContext()==null?"":advert.getContext());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}
	
	public int updateUrl(BlueAdvert advert) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_advert` SET url:=url ");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("url", advert.getUrl());
		sql.append(" WHERE sid =:sid");
		paramMap.put("sid", advert.getSid());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}

}

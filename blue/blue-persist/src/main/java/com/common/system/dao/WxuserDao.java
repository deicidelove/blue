package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.WxUserEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	微信用户表 增刪改查
 */
@Repository("wxuserDao")
public class WxuserDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public WxUserEntity seleteById(String openId){
		Assert.notNull(openId,"openId is null");
		String sql = " SELECT * FROM rc_a_wx_user WHERE open_id = :openId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		List<WxUserEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<WxUserEntity>(WxUserEntity.class));

		return !CollectionUtils.isEmpty(resultList) ? resultList.get(0): null;
	}
	
	public List<WxUserEntity> seleteList(Integer pageNum, Integer pageSize){
		String sql = " SELECT * FROM rc_a_wx_user WHERE limit :pageStartNum, :pageSize by create_time desc ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<WxUserEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(WxUserEntity.class));
		return result;
	}
	
	public List<WxUserEntity> seleteList(Integer pageNum, Integer pageSize, String superOpenId){
		String sql = " SELECT * FROM rc_a_wx_user WHERE super_open_id = :superOpenId order by create_time desc limit :pageStartNum, :pageSize  ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("superOpenId", superOpenId);
		List<WxUserEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(WxUserEntity.class));
		return result;
	}
	
	public List<WxUserEntity> seleteList(){
		String sql = " SELECT * FROM rc_a_wx_user WHERE 1=1  order by create_time desc";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<WxUserEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(WxUserEntity.class));
		return result;
	}
	
	public int save(WxUserEntity wxUserEntity){
		Assert.notNull(wxUserEntity	,"wxUserEntity is null");
		String sql = "INSERT INTO rc_a_wx_user"
				+ "( open_id, tel, super_open_id, qr_code_url )"
				+ " VALUES (:openId, :tel, :superOpenId, :qrCodeUrl)"
				+ " ON DUPLICATE KEY UPDATE tel=:tel,super_open_id=:superOpenId,qr_code_url=:qrCodeUrl ";
		return namedParameterJdbcTemplate.update(sql, Convert.beanToMap(wxUserEntity));
	}
	
	/**
	 * 更新用户信息
	 * @param openId 微信OpenId
	 * @param phoneNumber 电话号码
	 * @param userName 用户名
	 * @param ticket 微信邀请ticket
	 * @param qrcodeUrl 二维码地址
	 */
	public void updateUserInfo(String openId, String phoneNumber, String userName, String ticket, String qrcodeUrl) {
		Assert.notNull(openId, "openId should not be null");
		Assert.notNull(phoneNumber, "phoneNumber should not be null");
		Assert.notNull(ticket, "ticket should not be null");
		Assert.notNull(qrcodeUrl, "qrcodeUrl should not be null");
		
		String sql = "update rc_a_wx_user set tel=:tel, user_name=:userName, ticket=:ticket, qr_code_url=:qrcodeUrl where open_id=:openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("tel", phoneNumber);
		paramMap.put("userName", userName);
		paramMap.put("ticket", ticket);
		paramMap.put("openId", openId);
		paramMap.put("qrcodeUrl", qrcodeUrl);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	/**
	 * 更新用户二维码信息
	 * @param openId 微信OpenId
	 * @param ticket 微信邀请ticket
	 * @param qrcodeUrl 二维码地址
	 */
	public void updateUserQRCodeUrl(String openId, String ticket, String qrcodeUrl) {
		Assert.notNull(openId, "openId should not be null");
		Assert.notNull(ticket, "ticket should not be null");
		Assert.notNull(qrcodeUrl, "qrcodeUrl should not be null");
		
		String sql = "update rc_a_wx_user set ticket=:ticket, qr_code_url=:qrcodeUrl where open_id=:openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("ticket", ticket);
		paramMap.put("openId", openId);
		paramMap.put("qrcodeUrl", qrcodeUrl);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	/**
	 * 刪除并非真正刪除
	 * @param actId
	 */
	public void deleteById(String openId){
		Assert.notNull(openId,"actId is null");
		String sql = "UPDATE `rc_a_wx_user` SET `is_delete`='0' WHERE (`open_id`=:openId)";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	/**
	 * 更新积分
	 * @param wxUserEntity
	 */
	public void updateJifen(WxUserEntity wxUserEntity){
		Assert.notNull(wxUserEntity,"wxUserEntity is null");
		String sql = "UPDATE `rc_a_wx_user` "
				+ "SET `jifen`=:jifen "
				+ " WHERE `open_id`=:openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("jifen", wxUserEntity.getJifen());
		paramMap.put("openId", wxUserEntity.getOpenId());
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	/**
	 * 更新tip
	 * @param openId
	 * @param isShowTip
	 */
	public void updateTip(String openId, Boolean isShowTip){
		String sql = "UPDATE `rc_a_wx_user` "
				+ "SET `is_show_tip = :isShowTip`"
				+ "WHERE `open_id` = :openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		paramMap.put("isShowTip", isShowTip);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	/**
	 * 更新合成图片路径
	 * @param openId
	 * @param combinedPicturePath
	 */
	public void updateCombinedPicturePath(String openId, String combinedPicturePath){
		String sql = "UPDATE `rc_a_wx_user` "
				+ "SET `combined_picture_path` = :combinedPicturePath "
				+ "WHERE `open_id` = :openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		paramMap.put("combinedPicturePath", combinedPicturePath);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
}

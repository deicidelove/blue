/**
 * 
 */
package com.common.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.system.entity.BlueAdvert;

/**
 * @author amkong
 * 
 */
@Repository("advertDao")
public class AdvertDao {

	public List<BlueAdvert> findList(int type, int startRow, int limit) {
		return null;
	}

	public int findNum(int type) {
		return type;
	}

	public int deleteNum(int sid) {
		return sid;
	}

	public int addAdvert(BlueAdvert advert) {
		return 0;
	}

	public BlueAdvert findAdvert(int sid) {
		return null;
	}

	public int updateAdvert(BlueAdvert advert) {
		return 0;
	}

}

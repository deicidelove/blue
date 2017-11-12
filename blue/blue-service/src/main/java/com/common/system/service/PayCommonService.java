package com.common.system.service;

import java.util.Map;

public interface PayCommonService {

	Map< String , String> payGoods(String openId, String goodsId);
	
	Map< String , String> payCZ(String openId, String goodsId, Float czFre);
}

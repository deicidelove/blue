/**
 * 
 */
package com.common.system.dto;

import java.util.List;

import com.common.system.entity.BlueAdvert;

/**
 * @author amkong
 *
 */
public class AdvertDto {
	
	private BlueAdvert advert;
	
	private BlueAdvert advertNext;
	
	private boolean flag= true;

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the advert
	 */
	public BlueAdvert getAdvert() {
		return advert;
	}

	/**
	 * @param advert the advert to set
	 */
	public void setAdvert(BlueAdvert advert) {
		this.advert = advert;
	}

	/**
	 * @return the advertNext
	 */
	public BlueAdvert getAdvertNext() {
		return advertNext;
	}

	/**
	 * @param advertNext the advertNext to set
	 */
	public void setAdvertNext(BlueAdvert advertNext) {
		this.advertNext = advertNext;
	}

}

package com.common.system.handler;

import com.common.system.builder.TextBuilder;
import com.common.system.entity.JifenLogEntity;
import com.common.system.entity.WxDetailEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.JifenLogService;
import com.common.system.service.WxDetailService;
import com.common.system.service.WxUserBLueService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

	@Resource
	private WxUserBLueService wxUserBLueService;
	@Resource
	private WxDetailService wxDetailService;
	@Resource
	private JifenLogService jifenLogService;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);

        if (userWxInfo != null) {
            // TODO 可以添加关注用户到本地
        	try {
        		WxUserEntity wxUserEntity = wxUserBLueService.getById(userWxInfo.getOpenId());
        		if(null == wxUserEntity){
        			wxUserEntity = new WxUserEntity();
        			wxUserEntity.setOpenId(userWxInfo.getOpenId());
        		}
        		if(StringUtils.isNotBlank(wxMessage.getEventKey())){
        			String superOpenId = wxMessage.getEventKey().replaceAll("qrscene_", "");
        			if(!StringUtils.isBlank(superOpenId)
        					&& superOpenId.length()> 5){
        				
        				wxUserEntity.setSuperOpenId(superOpenId);
        				
        				WxUserEntity superWxUser = wxUserBLueService.getById(superOpenId);
        				if(null != superWxUser){
        					try {
								
        						JifenLogEntity jifenLogEntity = new JifenLogEntity();
        						jifenLogEntity.setOpenId(superOpenId);
        						jifenLogEntity.setJifen(3);
        						jifenLogEntity.setIsReverse(false);
        						jifenLogEntity.setType("qr_fx");
        						jifenLogService.save(jifenLogEntity);
        						superWxUser.setJifen(superWxUser.getJifen()+3);
        						wxUserBLueService.updateJifen(superWxUser);
							} catch (Exception e) {
								logger.error("分享二维码更新积分异常！",e);
							}
        				}
        			}
        		}
        		wxUserBLueService.save(wxUserEntity);
        		WxDetailEntity wxDetailEntity = wxDetailService.findByOpenId(userWxInfo.getOpenId());
        		if(null == wxDetailEntity){
        			wxDetailEntity = new WxDetailEntity();
        			wxDetailEntity.setOpenId(userWxInfo.getOpenId());
        			wxDetailEntity.setPic(userWxInfo.getHeadImgUrl());
        			wxDetailEntity.setSex(userWxInfo.getSex());
        			wxDetailEntity.setName(userWxInfo.getNickname());
        		}
        		wxDetailService.save(wxDetailEntity);
			} catch (Exception e) {
				this.logger.error("保存用户信息报错！", e);
			}
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("走进蓝鲟，微笑出行", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}

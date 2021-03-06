package com.common.system.controller.personal;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.common.system.dto.PersonalOrderDTO;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.entity.JifenLogEntity;
import com.common.system.entity.OrderEntity;
import com.common.system.entity.WxDetailEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.ActService;
import com.common.system.service.GivingService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.common.system.service.JifenLogService;
import com.common.system.service.OrderService;
import com.common.system.service.WxDetailService;
import com.common.system.service.WxUserBLueService;
import com.common.system.util.CookieUtil;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "personal")
public class PersonalController {

	@Resource
	private ActService actService;

	@Resource
	private GoodsService goodsService;

	@Resource
	private GoodsImgService goodsImgService;

	@Resource
	private GoodsConsumerRelateService goodsConsumerRelateService;

	@Resource
	private WxDetailService wxDetailService;

	@Resource
	private WxUserBLueService wxUserBLueService;

	@Resource
	private OrderService orderService;

	@Resource(name = "wxMpService")
    private WxMpService wxService;

	@Resource
	private GivingService givingService;
	
	@Resource
	private JifenLogService jifenLogService;

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonalController.class);
	
	// 通过ticket换取二维码
	public static final String ShowQRCodeURL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView,
			HttpServletRequest request) {
		modelAndView.setViewName("/personal/personal");
		/*
		 * try { if(StringUtils.isNoneBlank(code)){ WxMpOAuth2AccessToken token
		 * = wxService.oauth2getAccessToken(code);
		 * System.out.println(token.getOpenId()); } } catch (WxErrorException e)
		 * { LOG.error("	"); }
		 */
		String openId = CookieUtil.getCookieValue(request, "openId");
		WxUserEntity wxUserEntity = wxUserBLueService.getById(openId);
		WxDetailEntity wxDetailEntity = wxDetailService.findByOpenId(openId);
		modelAndView.addObject("wxUserEntity", wxUserEntity);
		modelAndView.addObject("wxDetailEntity", wxDetailEntity);
		return modelAndView;
	}

	@RequestMapping(value = "order", method = RequestMethod.GET)
	public ModelAndView order(ModelAndView modelAndView,
			HttpServletRequest request,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "50") int pageSize) {
		modelAndView.setViewName("/personal/personalorder");
		String openId = CookieUtil.getCookieValue(request, "openId");
		PageInfo<OrderEntity> result = orderService.seleteListByOpenId(openId,
				start, pageSize);
		List<PersonalOrderDTO> resultList = null;
		if(!CollectionUtils.isEmpty(result.getList())){
			resultList= Lists.newArrayListWithExpectedSize(result.getList().size());
			for(OrderEntity orderEntity : result.getList()){
				PersonalOrderDTO personalOrderDTO = new PersonalOrderDTO();
				personalOrderDTO.setOrderEntity(orderEntity);
				Result<GoodsEntity> goodsEntityResult = goodsService.getById(orderEntity.getGoodsId());
				if(null != goodsEntityResult.getData()){
					personalOrderDTO.setGoodsEntity(goodsEntityResult.getData());
					List<GoodsImgEntity> goodsImgEntityList = goodsImgService.findByGoodsId(goodsEntityResult.getData().getGoodsId(), "list_img");
					if(!CollectionUtils.isEmpty(goodsImgEntityList)){
						personalOrderDTO.setGoodsImgEntity(goodsImgEntityList.get(0));
					}
				}
				resultList.add(personalOrderDTO);
			}
		}
		modelAndView.addObject("resultList", resultList);
		return modelAndView;
	}
	
	@RequestMapping(value = "jifendetail", method = RequestMethod.GET)
	public ModelAndView jifendetail(ModelAndView modelAndView,
			HttpServletRequest request ,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "50") int pageSize) {
		modelAndView.setViewName("/personal/personaljifendetail");
		String openId = CookieUtil.getCookieValue(request, "openId");
		PageInfo<JifenLogEntity> jifenPage = jifenLogService.listForPage(start, pageSize, openId);
		modelAndView.addObject("resultList", jifenPage.getList());
		return modelAndView;
	}
	
	@RequestMapping(value = "buyjifen/", method = RequestMethod.GET)
	public ModelAndView buyjifen(ModelAndView modelAndView,
			HttpServletRequest request ) {
		modelAndView.setViewName("/personal/personalbuyjifen");
		String openId = CookieUtil.getCookieValue(request, "openId");
		return modelAndView;
	}
	
	@RequestMapping(value = "queryqr", method = RequestMethod.GET)
	public ModelAndView queryQR(ModelAndView modelAndView,
			HttpServletRequest request ) throws WxErrorException {
		String openId = CookieUtil.getCookieValue(request, "openId");
		WxUserEntity wxUserEntity = wxUserBLueService.getById(openId);
		Map<String, String> urlMap = Maps.newHashMap();
		
		modelAndView.setViewName("/personal/personalqrtemplate");
		if(null == wxUserEntity){
			modelAndView.addObject("urlMaps",JSON.toJSONString(urlMap));
			modelAndView.addObject("openId",openId);
			return modelAndView;
		}
		try {
			
			if(StringUtils.isBlank(wxUserEntity.getQrCodeUrl())){
				// 生成获取二维码图片并保存
				WxMpQrCodeTicket ticket = wxService.getQrcodeService().qrCodeCreateLastTicket(openId);
				String qrcodeUrl = ShowQRCodeURL + "?ticket=" + ticket.getTicket();
				wxUserEntity.setQrCodeUrl(qrcodeUrl);
				wxUserBLueService.updateUserQRCodeUrl(openId, ticket.getTicket(), qrcodeUrl);
			}
			
			String url = wxUserEntity.getCombinedPicturePath();
			
			if(StringUtils.isNotBlank(url)){
				urlMap = JSON.parseObject(url, 
						new TypeReference<HashMap<String, String>>() {
				});
			}
		} catch (Exception e) {
			LOG.error("获取二维码失败",e);
		}
		modelAndView.addObject("qrCodeUrl", wxUserEntity.getQrCodeUrl());
		modelAndView.addObject("urlMaps",JSON.toJSONString(urlMap));
		modelAndView.addObject("openId",openId);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "generateimg")
    public Map<String, String> generateimg( String type){
		Map<String, String> result = Maps.newHashMap();
		result.put("result_code", "success");
		result.put("result_msg", "success");
		try {
			
			
		} catch (Exception e) {
			LOG.error("生成自定义图片失败，请联系管理员！",e);
			result.put("result_code", "fail");
			result.put("result_msg", "生成自定义图片失败，请联系管理员！");
		}
        
        return result;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "payfail")
    public String payfail(String outTradeId ,HttpServletRequest request ) {
		Map<String, String> result =  Maps.newHashMap();
		try {
			result.put("result_code", "success");
			result.put("result_msg", "success");
			String openId = CookieUtil.getCookieValue(request, "openId");
			OrderEntity orderEntity = orderService.findByOutTradeId(outTradeId);
			if(null == orderEntity){
	    		result.put("result_code", "fail");
	        	result.put("result_msg", "支付失败！");
				return "failure";
			}
			WxUserEntity wxUserEntity = wxUserBLueService.getById(openId);
			if(null == wxUserEntity){
				return "failure";
			}
			wxUserEntity.setJifen(wxUserEntity.getJifen() - orderEntity.getJifen());
			//TODO
			JifenLogEntity jifenLogEntity = jifenLogService.getByOpenIdAndType(openId, "buy");
			if(null != jifenLogService){
				jifenLogService.deleteById(jifenLogEntity.getSid());
			}
			
		} catch (Exception e) {
			LOG.error("updateShowTip error!", e);
			return "failure";
		}
		return "success";
	}
	
	@RequestMapping(value = "invitation", method = RequestMethod.GET)
	public ModelAndView invitation(ModelAndView modelAndView,
			HttpServletRequest request,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "50") int pageSize) {
		modelAndView.setViewName("/personal/personalinvitation");
		String openId = CookieUtil.getCookieValue(request, "openId");
		PageInfo<WxUserEntity> invitatePage = wxUserBLueService.listForPage(start, pageSize, openId);
		List<WxDetailEntity> detailList = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(invitatePage.getList())){
			for(WxUserEntity wxUserEntity :invitatePage.getList()){
				WxDetailEntity wxDetailEntity = wxDetailService.findByOpenId(wxUserEntity.getOpenId());
				detailList.add(wxDetailEntity);
			}
		}
		
		modelAndView.addObject("resultList", detailList);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteOrder")
    public Map<String, String> delete( String orderId){
		Map<String, String> map = Maps.newHashMap();
		map.put("msg", "删除成功");
		try {
			
			Result<Integer> result = orderService.delete(Integer.valueOf(orderId));
		} catch (Exception e) {
			LOG.error("删除订单失败！",e);
			map.put("msg", "删除成功");
		}
        
        
        return map;
    }
	
}

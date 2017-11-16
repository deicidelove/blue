package com.common.system.controller.personal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.mp.api.WxMpService;

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
import com.common.system.service.WxUserService;
import com.common.system.util.CookieUtil;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
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
	private WxUserService wxUserService;

	@Resource
	private OrderService orderService;

	@Resource
	private WxMpService wxService;

	@Resource
	private GivingService givingService;
	
	@Resource
	private JifenLogService jifenLogService;

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonalController.class);

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
		WxUserEntity wxUserEntity = wxUserService.getById(openId);
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
			HttpServletRequest request ) {
		String openId = CookieUtil.getCookieValue(request, "openId");
		WxUserEntity wxUserEntity = wxUserService.getById(openId);
		modelAndView.setViewName("/personal/personalqrtemplate");
		if(null == wxUserEntity){
			return modelAndView;
		}
		String url = wxUserEntity.getQrCodeUrl();
		Map<String, String> urlMap = Maps.newHashMap();
		if(StringUtils.isNotBlank(url)){
			urlMap = JSON.parseObject(url, 
					new TypeReference<HashMap<String, String>>() {
					});
		}
		urlMap.put("1", "http");
		urlMap.put("2", "http");
		urlMap.put("3", "http");
		modelAndView.addObject("urlMap",urlMap);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "payfail")
    public String payfail(String outTradeId ,HttpServletRequest request ) {
		String openId = CookieUtil.getCookieValue(request, "openId");
		try {
			
			OrderEntity orderEntity = orderService.findByOutTradeId(outTradeId);
			if(null == orderEntity){
				return "failure";
			}
			WxUserEntity wxUserEntity = wxUserService.getById(openId);
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
		PageInfo<WxUserEntity> invitatePage = wxUserService.listForPage(start, pageSize, openId);
		modelAndView.addObject("resultList", invitatePage.getList());
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

package com.common.system.controller.personal;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.mp.api.WxMpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
import com.google.common.collect.Lists;

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
		// TODO
		openId = "1";
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
		// TODO
		openId = "1";

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
		// TODO
		openId = "1";
		PageInfo<JifenLogEntity> jifenPage = jifenLogService.listForPage(start, pageSize, openId);
		modelAndView.addObject("resultList", jifenPage);
		return modelAndView;
	}
	@RequestMapping(value = "buyjifen", method = RequestMethod.GET)
	public ModelAndView buyjifen(ModelAndView modelAndView,
			HttpServletRequest request ) {
		modelAndView.setViewName("/personal/personalbuyjifen");
		String openId = CookieUtil.getCookieValue(request, "openId");
		// TODO
		openId = "1";
		
		modelAndView.addObject("resultList", null);
		return modelAndView;
	}
	@RequestMapping(value = "invitation", method = RequestMethod.GET)
	public ModelAndView invitation(ModelAndView modelAndView,
			HttpServletRequest request,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "50") int pageSize) {
		modelAndView.setViewName("/personal/personalinvitation");
		String openId = CookieUtil.getCookieValue(request, "openId");
		// TODO
		openId = "1";
		PageInfo<WxUserEntity> invitatePage = wxUserService.listForPage(start, pageSize, openId);
		modelAndView.addObject("resultList", invitatePage.getList());
		return modelAndView;
	}
	
    @RequestMapping(value = "deleteorder/{orderId}",method = RequestMethod.GET)
    public @ResponseBody
    Result<Integer> delete(@PathVariable Integer orderId){
        Result<Integer> result = orderService.delete(orderId);
        return result;
    }
	
}

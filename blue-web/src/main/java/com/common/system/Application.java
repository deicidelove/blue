package com.common.system;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.util.CookieUtil;
import com.google.common.collect.Lists;

@EnableTransactionManagement
@SpringBootApplication
@RestController
public class Application {

	@Resource
	private WxMpService  wxService;
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
    @RequestMapping("/")
    public ModelAndView greeting(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView) {
    	 try {
    		 String code = request.getParameter("code");
    		 
		    	if(StringUtils.isNoneBlank(code)){
		    		WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
		    		CookieUtil.setCookie(response, "openId",token.getOpenId() );
		    	}
				} catch (WxErrorException e) {
					LOG.error("	获取微信信息异常！",e);
				}
        modelAndView.setViewName("/index");
		return modelAndView;
    }

    public static void main(String[] args) {
    	ApplicationContext ctx = SpringApplication.run(Application.class, args);
    	
    	try {
    		WxMpService wxMpService = ctx.getBean("wxMpService",WxMpService.class);
    		WxMenu wxMenu = new WxMenu();
    		List<WxMenuButton> buttons = Lists.newArrayList();
    		WxMenuButton wxMenuButton = new WxMenuButton();
    		wxMenuButton.setName("蓝鲟首页");
    		wxMenuButton.setUrl("http://wx.njlxkq.com/");
    		wxMenuButton.setKey("lanxun_01");
    		wxMenuButton.setType("click");
    		
    		buttons.add(wxMenuButton);
    		wxMenu.setButtons(buttons);
    		wxMpService.getMenuService().menuCreate(wxMenu);
    		
		} catch (Exception e) {
			LOG.error("	创建菜单失败！",e);
		}
    	
    	
    }
}
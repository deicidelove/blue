package com.common.system;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.service.WxUserBLueService;
import com.google.common.collect.Lists;

@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
@RestController
public class Application {

	@Resource(name = "wxMpService")
    private WxMpService wxService;
	
	@Resource
	private  WxUserBLueService wxUserBLueService;
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
    @RequestMapping("/")
    public ModelAndView greeting(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView) {
        modelAndView.setViewName("/index");
		return modelAndView;
    }

    public static void main(String[] args) {
    	ApplicationContext ctx = SpringApplication.run(Application.class, args);
    	
    	try {
    		WxMpService wxMpService = ctx.getBean("wxMpService",WxMpService.class);
    		WxMenu wxMenu = new WxMenu();
    		List<WxMenuButton> buttons = Lists.newArrayList();
    		WxMenuButton wxMenuButton1 = new WxMenuButton();
    		wxMenuButton1.setName("走进蓝鲟");
    		List<WxMenuButton> subButtons1 = Lists.newArrayList();
    		WxMenuButton subButton1 = new WxMenuButton();
    		subButton1.setName("3D全景");
//    		subButton1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcf685d2194e26db2&redirect_uri=http%3a%2f%2fwx.njlxkq.com&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
    		subButton1.setUrl("http://wx.njlxkq.com");
    		subButton1.setType("view");
    		subButtons1.add(subButton1);
    		wxMenuButton1.setSubButtons(subButtons1);
    		buttons.add(wxMenuButton1);
    		
    		WxMenuButton wxMenuButton2 = new WxMenuButton();
    		wxMenuButton2.setName("蓝鲟官网");
    		List<WxMenuButton> subButtons2 = Lists.newArrayList();
    		WxMenuButton subButton2 = new WxMenuButton();
    		subButton2.setName("官网");
//    		subButton2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcf685d2194e26db2&redirect_uri=http%3a%2f%2fwx.njlxkq.com&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
    		subButton2.setUrl("http://wx.njlxkq.com");
    		subButton2.setType("view");
    		subButtons2.add(subButton2);
    		wxMenuButton2.setSubButtons(subButtons2);
    		buttons.add(wxMenuButton2);
    		
    		WxMenuButton wxMenuButton3 = new WxMenuButton();
    		wxMenuButton3.setName("最近活动");
    		List<WxMenuButton> subButtons3 = Lists.newArrayList();
    		WxMenuButton subButton3 = new WxMenuButton();
    		subButton3.setName("最近活动");
//    		subButton3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcf685d2194e26db2&redirect_uri=http%3a%2f%2fwx.njlxkq.com&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
    		subButton3.setUrl("http://wx.njlxkq.com/lastact/index");
    		subButton3.setType("view");
    		subButtons3.add(subButton3);
    		wxMenuButton3.setSubButtons(subButtons3);
    		buttons.add(wxMenuButton3);
    		
    		wxMenu.setButtons(buttons);
    		wxMpService.getMenuService().menuCreate(wxMenu);
    		
		} catch (Exception e) {
			LOG.error("	创建菜单失败！",e);
		}
    	
    	
    }
}
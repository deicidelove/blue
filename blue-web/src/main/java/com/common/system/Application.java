package com.common.system;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.util.CookieUtil;

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
        SpringApplication.run(Application.class, args);
    }
}
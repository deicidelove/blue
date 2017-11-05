package com.common.system;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.util.CookieUtil;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public ModelAndView greeting(HttpServletResponse response,ModelAndView modelAndView) {
//    	 try {
//    	if(StringUtils.isNoneBlank(code)){
//    		WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
//    		CookieUtil.setCookie(response, "openId",token.getOpenId() );
//    		System.out.println(token.getOpenId());
//    	}
//		} catch (WxErrorException e) {
//			LOG.error("	");
//		}
    	CookieUtil.setCookie(response, "openId","test" );
        modelAndView.setViewName("/index");
		return modelAndView;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
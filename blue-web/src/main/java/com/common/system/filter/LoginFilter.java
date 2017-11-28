package com.common.system.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.common.system.entity.WxUserEntity;
import com.common.system.service.WxUserBLueService;
import com.common.system.util.CookieUtil;


@Order(1)
//重点
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter{

	@Resource(name = "wxMpService")
    private WxMpService wxService;
	
	@Resource
	private  WxUserBLueService wxUserBLueService;
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getServletPath().indexOf(".") >0 
				|| req.getServletPath().indexOf("login") >0){
			filterChain.doFilter(request, response);
			return;
		}
	   	 try {
			 String code = request.getParameter("code");
			 String openId = CookieUtil.getCookieValue((HttpServletRequest)request, "openId");
			 if(StringUtils.isEmpty(openId)
					 && StringUtils.isBlank(code)){
				 ((HttpServletResponse) response).sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcf685d2194e26db2&redirect_uri=http%3a%2f%2fwx.njlxkq.com&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			 }
			 if(StringUtils.isEmpty(openId)){
				 if(StringUtils.isNotBlank(code) ){
					 WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
					 CookieUtil.setCookie((HttpServletResponse)response, "openId",token.getOpenId() );
					 openId = token.getOpenId();
				 }
			 }
			 if(StringUtils.isNotBlank(openId)){
				 WxUserEntity wxUserEntity = wxUserBLueService.getById(openId);
				 if(StringUtils.isBlank(wxUserEntity.getTel())){
					 CookieUtil.setCookie((HttpServletResponse)response, "original_url", req.getRequestURL().toString());
					 ((HttpServletResponse) response).sendRedirect("http://wx.njlxkq.com/login/register");
				 }
			 }
			 
		} catch (WxErrorException e) {
			LOG.error("	获取微信信息异常！",e);
		}
	   	filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

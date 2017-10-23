/**
 * 
 */
package com.common.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueStaff;
import com.common.system.service.AdvertService;
import com.common.system.service.CommonService;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
public class NoticeController {
@Resource
private CommonService commonService;

@Resource
private AdvertService advertService;

@RequestMapping("/noticePage")
public ModelAndView noticeListPage(ModelAndView modelAndView) {

	List<BlueAdvert> adverts = commonService.findAdvert(1);
	modelAndView.addObject("advertsFirst", adverts==null?null:adverts.get(0));
	modelAndView.addObject("adverts", adverts);
	modelAndView.setViewName("/html/notice");
	return modelAndView;
}

@RequestMapping(value = "noticeDetialPage/{sid}", method = RequestMethod.GET)
public ModelAndView deptDetialById(ModelAndView modelAndView,
		@PathVariable Integer sid) {
	Result<BlueAdvert> advert = advertService.findAdvert(sid);
    modelAndView.addObject("advert",advert.getData());
    modelAndView.setViewName("/html/noticeDetial");
    return modelAndView;

}
	
	
	
}

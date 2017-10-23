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

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueEncyclopedias;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.EncyclopediasService;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
public class EncyclopediasController {
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private EncyclopediasService encyclopediasService;
	
	@RequestMapping("/encyclopediasPage")
	public ModelAndView deptListPage(ModelAndView modelAndView) {

		List<BlueEncyclopedias> encyclopedias = commonService.findEncyclopedias(0);
		List<BlueEncyclopedias> encyclopediasjx = commonService.findEncyclopedias(1);
		modelAndView.addObject("encyclopedias", encyclopedias);
		modelAndView.addObject("encyclopediasjx", encyclopediasjx);
		modelAndView.setViewName("/html/encyclopedias");
		return modelAndView;
	}

	@RequestMapping(value = "encyclopediasDetial/{sid}", method = RequestMethod.GET)
	public ModelAndView deptDetialById(ModelAndView modelAndView,
			@PathVariable Integer sid) {
		Result<BlueEncyclopedias> result = encyclopediasService.findEncyclopedias(sid);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/html/encyclopediasDetial");
        return modelAndView;

	}

}

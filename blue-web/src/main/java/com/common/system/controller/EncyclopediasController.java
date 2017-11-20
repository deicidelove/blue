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
import com.common.system.entity.BlueEncyclopedias;
import com.common.system.entity.BlueProjectAdvert;
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
		try {
			modelAndView.addObject("encyclopediasjx0", encyclopediasjx.get(0));
			modelAndView.addObject("encyclopediasjx1", encyclopediasjx.get(1));
			modelAndView.addObject("encyclopediasjx2", encyclopediasjx.get(2));
			modelAndView.addObject("encyclopediasjx3", encyclopediasjx.get(3));
		} catch (Exception e) {
		}
		try {
			modelAndView.addObject("encyclopedias0", encyclopedias.get(0));
			modelAndView.addObject("encyclopedias1", encyclopedias.get(1));
			modelAndView.addObject("encyclopedias2", encyclopedias.get(2));
			modelAndView.addObject("encyclopedias3", encyclopedias.get(3));
		} catch (Exception e) {
		}
		//口腔百科轮播图
	 	List<BlueProjectAdvert> encyclopedyLBT = commonService.findTypeAdvert(7);
	 	//口腔百科中间广告
	 	List<BlueProjectAdvert> encyclopedyMiddle = commonService.findTypeAdvert(8);
	 	modelAndView.addObject("encyclopedyLBT", encyclopedyLBT);
	 	modelAndView.addObject("encyclopedyMiddle", encyclopedyMiddle);
		modelAndView.setViewName("/html/encyclopedias");
		return modelAndView;
	}
	
	@RequestMapping("/encyAll")
	public ModelAndView encyAllListPage(ModelAndView modelAndView) {

		List<BlueEncyclopedias> encyclopedias = commonService.findEncyclopedias(-1);
		modelAndView.addObject("encys", encyclopedias);
		modelAndView.setViewName("/html/encyclopedyList");
		return modelAndView;
	}

	@RequestMapping(value = "encyclopediasDetial/{sid}", method = RequestMethod.GET)
	public ModelAndView deptDetialById(ModelAndView modelAndView,
			@PathVariable Integer sid) {
		Result<BlueEncyclopedias> result = encyclopediasService.findEncyclopedias(sid);
        modelAndView.addObject("bean",result.getData() == null?new BlueEncyclopedias():result.getData());
        modelAndView.setViewName("/html/encyclopediasDetial");
        return modelAndView;

	}

}

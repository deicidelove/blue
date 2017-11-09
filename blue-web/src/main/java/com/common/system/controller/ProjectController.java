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
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.ProjectService;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
public class ProjectController {
	@Resource
	private ProjectService projectService;
	
	@Resource
	private CommonService commonService;
	
	
	@RequestMapping("/projectPage")
	public ModelAndView projectPage(ModelAndView modelAndView){
		List<BlueProject> projects = commonService.findProjects();
		try {
			modelAndView.addObject("project0", projects.get(0));
			modelAndView.addObject("project1", projects.get(1));
			modelAndView.addObject("project2", projects.get(2));
			modelAndView.addObject("project3", projects.get(3));
			modelAndView.addObject("project4", projects.get(4));
			modelAndView.addObject("project5", projects.get(5));
			modelAndView.addObject("project6", projects.get(6));
			modelAndView.addObject("project7", projects.get(7));
		} catch (Exception e) {
		}
		modelAndView.setViewName("/html/project");
		return modelAndView;
	}
	
	@RequestMapping("/projectPageAll")
	public ModelAndView projectPageAll(ModelAndView modelAndView){
		List<BlueProject> projects = commonService.findProjects();
		modelAndView.addObject("projects", projects);
		modelAndView.addObject("projectFirst", projects==null?projects:projects.get(0));
		modelAndView.setViewName("/html/projectAll");
		return modelAndView;
	}
	
	@RequestMapping(value = "projectDetial/{sid}", method = RequestMethod.GET)
	public ModelAndView deptDetialById(ModelAndView modelAndView,
			@PathVariable Integer sid) {
		Result<BlueProject> result = projectService.findProject(sid);
        modelAndView.addObject("project",result.getData());
        modelAndView.setViewName("/html/projectDetial");
        return modelAndView;

	}


}

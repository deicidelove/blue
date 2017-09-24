/**
 * 
 */
package com.common.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.system.entity.BlueProject;
import com.common.system.service.ProjectService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("project")
public class ProjectController {

	@Resource
	private ProjectService projectService;

	@RequestMapping("/findProjectList")
	public PageBean<BlueProject> findProjectList(int startPage, int limitLength) {
		return projectService.findProjectList(startPage, limitLength);
	}

	@RequestMapping("/deleteProject")
	public Result<String> deleteProject(int sid) {
		return projectService.deleteProject(sid);
	}

	@RequestMapping("/updateProject")
	public Result<String> updateProject(String name, String context, int sid) {
		return projectService.updateProject(name, context, sid);

	}

	@RequestMapping("/addProject")
	public Result<String> addProject(String name, String context) {
		return projectService.addProject(name, context);
	}

	@RequestMapping("/findProject")
	public Result<BlueProject> findProject(int sid) {
		return projectService.findProject(sid);
	}
}

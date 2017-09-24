/**
 * 
 */
package com.common.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.system.entity.BlueProject;

/**
 * @author amkong
 * 
 */
@Repository("projectDao")
public class ProjectDao {

	public int findCount() {
		return 0;
	}

	public List<BlueProject> findProjectList(int startRow, int limitLength) {
		return null;
	}

	public int deleteProject(int sid) {
		return sid;
	}

	public int addProject(BlueProject project) {
		return 0;
	}

	public int updateProject(BlueProject project) {
		return 0;
	}

	public BlueProject findProject(int sid) {
		return null;
	}

}

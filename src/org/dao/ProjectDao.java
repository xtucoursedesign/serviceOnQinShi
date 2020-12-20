package org.dao;

import java.util.List;

import org.bean.Project;

public interface ProjectDao {
	Project getProject(String pid);
	List<Project> getAllProject();
	int insertProject(Project p);
	int updateProject(Project p, String oldPid);
	int removeProject(String pid);
}

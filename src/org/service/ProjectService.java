package org.service;

import java.util.List;

import org.bean.Project;
import org.bean.Sketch;

public interface ProjectService {
	Project getProject(String pid);
	List<Project> getAllProject();
	int insertProject(Project p);
	int updateProject(Project p, String oldPid);
	boolean removeProject(String pid);
	List<Sketch> getSketchByProId(String proId);
}

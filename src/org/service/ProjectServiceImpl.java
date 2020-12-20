package org.service;

import java.util.ArrayList;
import java.util.List;

import org.bean.Project;
import org.bean.Sketch;
import org.dao.AssemblyDao;
import org.dao.LayoffDao;
import org.dao.PreassemblyDao;
import org.dao.ProjectDao;
import org.dao.impl.AssemblyDaoImpl;
import org.dao.impl.LayoffDaoImpl;
import org.dao.impl.PreassemblyDaoImpl;
import org.dao.impl.ProjectDaoImpl;

public class ProjectServiceImpl implements ProjectService {
	private ProjectDao projectDao = new ProjectDaoImpl();

	@Override
	public Project getProject(String pid) {
		return projectDao.getProject(pid);
	}

	@Override
	public List<Project> getAllProject() {
		return projectDao.getAllProject();
	}

	@Override
	public int insertProject(Project p) {
		return projectDao.insertProject(p);
	}

	@Override
	public int updateProject(Project p, String oldPid) {
		return projectDao.updateProject(p, oldPid);
	}

	@Override
	public boolean removeProject(String pid) {
		int len = projectDao.removeProject(pid);
		return len > 0;
	}

	@Override
	public List<Sketch> getSketchByProId(String proId) {
		LayoffDao ld = new LayoffDaoImpl();
		AssemblyDao ad = new AssemblyDaoImpl();
		PreassemblyDao pd = new PreassemblyDaoImpl();
		int status[] = {ld.getStatusByProId(proId), pd.getStatusByProId(proId), ad.getStatusByProId(proId)};
		String steps[] = {"layoff", "preassembly", "assembly"};
		List<Sketch> list = new ArrayList<>();
		for(int i = 0; i < status.length; i++) {
			list.add(new Sketch(steps[i], status[i]));
		}
		return list;
	}

}

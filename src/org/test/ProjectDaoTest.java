package org.test;

import org.bean.Project;
import org.dao.ProjectDao;
import org.dao.impl.ProjectDaoImpl;
import org.junit.jupiter.api.Test;

class ProjectDaoTest {
	private ProjectDao projectDao = new ProjectDaoImpl();

	@Test
	void testGetProject() {
		System.out.println(projectDao.getProject("00000000001"));
	}

	@Test
	void testGetAllProject() {
		System.out.println(projectDao.getAllProject());
	}
	
	@Test
	void testInsertProject() {
		Project p = new Project("3", "2", "2", "5", "2", "2020-12-17 20:53:47", "2020-12-17 20:53:47", "2020-12-17 20:53:47");
		projectDao.insertProject(p);
	}

}

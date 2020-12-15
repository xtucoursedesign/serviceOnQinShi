package org.test;

import org.bean.Main;
import org.bean.Page;
import org.dao.MainDao;
import org.dao.impl.MainDaoImpl;
import org.junit.jupiter.api.Test;

class MainDaoTest {
	private MainDao mainDao = new MainDaoImpl();

	@Test
	void testGetMain() {
		Main main = mainDao.getMain("00000000001");
		System.out.println(main);
	}

	@Test
	void testInsertMain() {
		Main main = new Main("00000000002", "¿ÌœÎ", "1", "23", "3", "3", "fsdf", "kg");
		mainDao.insertMain(main);
	}

	@Test
	void testRemoveMain() {
		mainDao.removeMain("00000000004");
	}

	@Test
	void testUpdateMain() {
		Main main = new Main("00000000004", "23", "1", "23", "3", "3", "fsdf", "kg");
		mainDao.updateMain(main, "00000000002");
	}

	@Test
	void testGetMainByPage() {
		Page<Main> p = new Page<>();
		p.setCurrent(1);
		Page<Main> mainByPage = mainDao.getMainByPage(p);
		System.out.println(mainByPage.getList());
	}

	@Test
	void testGetMainBySearch() {
		
	}

}

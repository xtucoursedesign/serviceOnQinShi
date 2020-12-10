package org.test;

import java.util.List;

import org.bean.Factory;
import org.dao.FactoryDao;
import org.dao.impl.FactoryDaoImpl;
import org.junit.jupiter.api.Test;

class FactoryDaoTest {
	private FactoryDao factoryDao = new FactoryDaoImpl();

	@Test
	void testGetFactory() {
		System.out.println(factoryDao.getFactory("00000000001"));
	}

	@Test
	void testUpdateFactory() {
		factoryDao.updateFactory(new Factory("00000000004", "四分厂"), "00000000004");
	}

	@Test
	void testGetAllFactory() {
		List<Factory> list = factoryDao.getAllFactory();
		for(Factory f : list) {
			System.out.println(f);
		}
	}

	@Test
	void testRemoveFactory() {
		factoryDao.removeFactory("00000000004");
	}

	@Test
	void testInsertFactory() {
		factoryDao.insertFactory(new Factory("00000000004", "4分厂"));
	}

}

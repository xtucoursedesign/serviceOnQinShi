package org.test;

import org.bean.User;
import org.dao.UserDao;
import org.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

class UserDaoTest {
	
	private UserDao userDao = new UserDaoImpl();

	@Test
	void testGetUser() {
		User user = userDao.getUser("lisi", "123456");
		System.out.println(user);
	}

	@Test
	void testUpdateUser() {
	}

	@Test
	void testGetAllUser() {
	}

	@Test
	void testRemoveUser() {
	}

	@Test
	void testInsertUser() {
		User user = new User("00000002", "lisi", "123456", "李四", "12645874125", "00000000002", "人事部");
		userDao.insertUser(user);
	}

}

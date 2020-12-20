package org.service;

import java.util.List;

import org.bean.User;
import org.dao.UserDao;
import org.dao.impl.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User getUser(String uname, String psw) {
		User user = userDao.getUser(uname, psw);
		if(user != null) {
			user.setPassword("");
		}
		return user;
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUser(String uid) {
		return userDao.getUser(uid);
	}

	@Override
	public int updateUser(User user, String oldUid) {
		return userDao.updateUser(user, oldUid);
	}

	@Override
	public boolean removeUser(String uid) {
		int len = userDao.removeUser(uid);
		return len > 0;
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

}

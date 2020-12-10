package org.dao;

import java.util.List;

import org.bean.User;

public interface UserDao {
	User getUser(String uname, String psw);
	User getUser(String uid);
	int updateUser(User user, String oldUid);
	List<User> getAllUser();
	int removeUser(String uid);
	int insertUser(User user);
}

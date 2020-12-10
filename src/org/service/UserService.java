package org.service;

import java.util.List;

import org.bean.User;

public interface UserService {
	User getUser(String uname, String psw);
	List<User> getAllUser();
	User getUser(String uid);
	int updateUser(User user, String oldUid);
	boolean removeUser(String uid);
	int insertUser(User user);
}

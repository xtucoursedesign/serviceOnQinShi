package org.dao.impl;

import java.util.List;

import org.bean.User;
import org.dao.BaseDao;
import org.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(String uname, String psw) {
		String sql = "SELECT * FROM pro_manage.users WHERE username=? AND PASSWORD=MD5(?)";
		return this.getBean(sql, uname, psw);
	}
	
	@Override
	public User getUser(String uid) {
		String sql = "SELECT * FROM pro_manage.users WHERE uid=?";
		return this.getBean(sql, uid);
	}

	@Override
	public int updateUser(User user, String oldUid) {
		User u = getUser(user.getUid());
		if(u != null && !user.getUid().equals(oldUid)) {
			return -1;
		}
		String sql = "UPDATE pro_manage.users SET uid=?,username=?,password=MD5(?),name=?,phone=?,faid=?,department=? WHERE uid=?";
		return this.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getPhone(), user.getFaid(), user.getDepartment(), oldUid);
	}

	@Override
	public List<User> getAllUser() {
		String sql = "SELECT * FROM pro_manage.users WHERE username != ?";
		return this.getBeanList(sql, "admin");
	}

	@Override
	public int removeUser(String uid) {
		String sql = "DELETE FROM pro_manage.users WHERE uid=?";
		return this.update(sql, uid);
	}

	@Override
	public int insertUser(User user) {
		if(getUser(user.getUid()) != null) {
			return -1;
		}
		String sql = "INSERT INTO pro_manage.users(uid,username,password,name,phone,faid,department) VALUES(?,?,MD5(?),?,?,?,?)";
		return this.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getPhone(), user.getFaid(), user.getDepartment());
	}
}

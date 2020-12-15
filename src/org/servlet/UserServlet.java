package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bean.DataJson;
import org.bean.MessageJson;
import org.bean.User;
import org.service.UserService;
import org.service.UserServiceImpl;

import com.google.gson.Gson;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private Gson gson = new Gson();

    
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		User user = userService.getUser(username, password);
		
		if(user != null) {
			String token1 = UUID.randomUUID().toString().replace("-", "");
			String token2 = UUID.randomUUID().toString().replace("-", "");
			String token = token1 + token2;
			session.setAttribute("token", token);
			Cookie cookie = new Cookie("JSESSIONID",session.getId());
			// 设置cookie 7 天有效
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user", user);
			map.put("token", token);
			
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "用户名或密码错误!")));
		}
	}
	
	protected void getAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = userService.getAllUser();
		if(list.size() != 0) {
			for(User u : list) {
				u.setPassword(null);
			}
			Map<String, List<User>> map = new HashMap<>();
			map.put("users", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		if(password == null || !password.equals(rePassword)) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "前后密码不一致!")));
			return;
		}
		String uid = request.getParameter("uid");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String faid = request.getParameter("faid");
		String department = request.getParameter("department");
		String oldUid = request.getParameter("oldUid");
		int update = userService.updateUser(new User(uid, username, password, name, phone, faid, department), oldUid);
		if(update > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(update == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		if(password == null || !password.equals(rePassword)) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "前后密码不一致!")));
			return;
		}
		String uid = request.getParameter("uid");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String faid = request.getParameter("faid");
		String department = request.getParameter("department");
		int insert = userService.insertUser(new User(uid, username, password, name, phone, faid, department));
		if(insert > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(insert == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void removeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		boolean isRemove = userService.removeUser(uid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}

}

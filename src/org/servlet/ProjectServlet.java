package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.DataJson;
import org.bean.MessageJson;
import org.bean.Project;
import org.bean.Sketch;
import org.service.ProjectService;
import org.service.ProjectServiceImpl;

import com.google.gson.Gson;

public class ProjectServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService = new ProjectServiceImpl();
	private Gson gson = new Gson();
       
	protected void getAllProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Project> list = projectService.getAllProject();
		if(list.size() != 0) {
			Map<String,List<Project>> map = new HashMap<>();
			map.put("project", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void insertProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String faid = request.getParameter("faid");
		String investid = request.getParameter("investid");
		String investname = request.getParameter("investname");
		String approdate = request.getParameter("approdate");
		String spededate = request.getParameter("spededate");
		String actdate = request.getParameter("actdate");
		
		Project p = new Project(pid, name, faid, investid, investname, approdate, spededate, actdate);
		int len = projectService.insertProject(p);
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void updateProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String faid = request.getParameter("faid");
		String investid = request.getParameter("investid");
		String investname = request.getParameter("investname");
		String approdate = request.getParameter("approdate");
		String spededate = request.getParameter("spededate");
		String actdate = request.getParameter("actdate");
		String oldPid = request.getParameter("oldPid");
		
		Project p = new Project(pid, name, faid, investid, investname, approdate, spededate, actdate);
		int len = projectService.updateProject(p, oldPid);
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void removeProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		boolean isRemove = projectService.removeProject(pid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}
	
	protected void getSketchByProId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		List<Sketch> list = projectService.getSketchByProId(pid);
		
		Map<String, List<Sketch>> map = new HashMap<>();
		map.put("sketch", list);
		
		response.getWriter().write(gson.toJson(new DataJson(0, map)));
	}
	
	protected void getProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		Project p = projectService.getProject(pid);
		
		Map<String, Project> map = new HashMap<>();
		map.put("project", p);
		
		response.getWriter().write(gson.toJson(new DataJson(0, map)));
	}
}

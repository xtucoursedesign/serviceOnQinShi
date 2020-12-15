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
import org.bean.Page;
import org.bean.Part;
import org.service.PartService;
import org.service.PartServiceImpl;

import com.google.gson.Gson;

public class PartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PartService partService = new PartServiceImpl();
	private Gson gson = new Gson();
       
	protected void getAllPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Part> list = partService.getAllPart();
		if(list.size() == 0) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "暂无数据!")));
		}else {
			Map<String,List<Part>> map = new HashMap<>();
			map.put("part", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}
	}
	
	protected void getPartByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		int pNo = 1;
		try {
			pNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.getWriter().write(gson.toJson(new MessageJson(1, "页码出错!")));
			return;
		}
		
		Page<Part> p = partService.getPartByPage(pNo);
		List<Part> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Part>> map = new HashMap<>();
			map.put("part", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void getPartBySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String searchType = request.getParameter("searchType");
		String searchKeyWord = request.getParameter("searchKeyWord");
		searchKeyWord = searchKeyWord.trim();
		if("".equals(searchKeyWord)) {
			getPartByPage(request,response);
			return;
		}
		System.out.println(searchKeyWord);
		int pNo = 1;
		try {
			pNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.getWriter().write(gson.toJson(new MessageJson(1, "页码出错!")));
			return;
		}
		
		Page<Part> p = partService.getPartBySearch(pNo, searchType, searchKeyWord);
		List<Part> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Part>> map = new HashMap<>();
			map.put("part", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void removePart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		boolean isRemove = partService.removePart(pid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}
	
	protected void insertPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String material = request.getParameter("material");
		String specifi = request.getParameter("specifi");
		String texture = request.getParameter("texture");
		String weight = request.getParameter("weight");
		String img = request.getParameter("img");
		String unit = request.getParameter("unit");
		
		Part p = new Part(pid, name, material, specifi, texture, weight, img, unit);
//		System.out.println(p);
		
		int len = partService.insertPart(p);
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void updatePart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String material = request.getParameter("material");
		String specifi = request.getParameter("specifi");
		String texture = request.getParameter("texture");
		String weight = request.getParameter("weight");
		String img = request.getParameter("img");
		String unit = request.getParameter("unit");
		String oldPid = request.getParameter("oldPid");
		
		Part p = new Part(pid, name, material, specifi, texture, weight, img, unit);
		int len = partService.updatePart(p, oldPid);
		
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(len == -1){
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
}

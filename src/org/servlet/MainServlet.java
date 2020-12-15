package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.DataJson;
import org.bean.Main;
import org.bean.MessageJson;
import org.bean.Page;
import org.service.MainService;
import org.service.MainServiceImpl;

import com.google.gson.Gson;

public class MainServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private MainService mainService = new MainServiceImpl();
	private Gson gson = new Gson();
	
	protected void getMainByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		int pNo = 1;
		try {
			pNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.getWriter().write(gson.toJson(new MessageJson(1, "页码出错!")));
			return;
		}
		
		Page<Main> p = mainService.getMainByPage(pNo);
		List<Main> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Main>> map = new HashMap<>();
			map.put("main", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
      
	protected void getMainBySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String searchType = request.getParameter("searchType");
		String searchKeyWord = request.getParameter("searchKeyWord");
		if("".equals(searchKeyWord.trim())) {
			getMainByPage(request,response);
			return;
		}
		int pNo = 1;
		try {
			pNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.getWriter().write(gson.toJson(new MessageJson(1, "页码出错!")));
			return;
		}
		
		Page<Main> p = mainService.getMainBySearch(pNo, searchType, searchKeyWord);
		List<Main> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Main>> map = new HashMap<>();
			map.put("main", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void removeMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		boolean isRemove = mainService.removeMain(mid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}
	
	protected void insertMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String name = request.getParameter("name");
		String material = request.getParameter("material");
		String specifi = request.getParameter("specifi");
		String texture = request.getParameter("texture");
		String weight = request.getParameter("weight");
		String img = request.getParameter("img");
		String unit = request.getParameter("unit");
		
		Main m = new Main(mid, name, material, specifi, texture, weight, img, unit);
//		System.out.println(p);
		
		int len = mainService.insertMain(m);
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void updateMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String name = request.getParameter("name");
		String material = request.getParameter("material");
		String specifi = request.getParameter("specifi");
		String texture = request.getParameter("texture");
		String weight = request.getParameter("weight");
		String img = request.getParameter("img");
		String unit = request.getParameter("unit");
		String oldMid = request.getParameter("oldMid");
		
		Main p = new Main(mid, name, material, specifi, texture, weight, img, unit);
		int len = mainService.updateMain(p, oldMid);
		
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(len == -1){
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}

}

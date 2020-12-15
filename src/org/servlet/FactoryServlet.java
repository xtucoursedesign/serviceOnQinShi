package org.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.DataJson;
import org.bean.Factory;
import org.bean.MessageJson;
import org.service.FactoryService;
import org.service.FactoryServiceImpl;

import com.google.gson.Gson;

public class FactoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private FactoryService factoryService = new FactoryServiceImpl();
	private Gson gson = new Gson();
       
	protected void getAllFactory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, List<Factory>> map = new LinkedHashMap<>();
		List<Factory> list = factoryService.getAllFactory();
		if(list.size() != 0) {
			map.put("factories", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void insertFactory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bfid = request.getParameter("bfid");
		String name = request.getParameter("name");
		
		int insert = factoryService.insertFactory(new Factory(bfid, name));
		if(insert > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(insert == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void removeFactory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bfid = request.getParameter("bfid");
		boolean isRemove = factoryService.removeFactory(bfid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}
	
	protected void updateFactory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bfid = request.getParameter("bfid");
		String name = request.getParameter("name");
		String oldBfid = request.getParameter("oldBfid");

		int update = factoryService.updateFactory(new Factory(bfid, name), oldBfid);
		if(update > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(update == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
//	protected void getFactory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String bfid = request.getParameter("bfid");
//		Map<String, Factory> map = new HashMap<>();
//		map.put("factory", factoryService.getFactory(bfid));
//		response.getWriter().write(gson.toJson(new DataJson(0, map)));
//	}
}

package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.DataJson;
import org.bean.Layoff;
import org.bean.MessageJson;
import org.service.LayoffService;
import org.service.LayoffServiceImpl;

import com.google.gson.Gson;

public class LayoffServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private LayoffService layoffService = new LayoffServiceImpl();
	private Gson gson = new Gson();

	protected void insertLayoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lid = request.getParameter("lid");
		String baitdate = request.getParameter("baitdate");
		String partid = request.getParameter("partid");
		String co = request.getParameter("count");
		String mainid = request.getParameter("mainid");
		String comp = request.getParameter("complete");
		String proid = request.getParameter("proid");
		String weight = request.getParameter("weight");
		String unit = request.getParameter("unit");
		String note = request.getParameter("note");
		
		Integer count = 0;
		Integer complete = 0;
		count = Integer.parseInt(co);
		complete = Integer.parseInt(comp);
		
		Layoff l = new Layoff(lid, baitdate, partid, count, mainid, complete, proid, weight, unit, note);
		
		int len = layoffService.insertLayoff(l);
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void getLayoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lid = request.getParameter("lid");
		
		Map<String, Layoff> map = new HashMap<>();
		map.put("layoff", layoffService.getLayoff(lid));
		response.getWriter().write(gson.toJson(new DataJson(0, map)));
	}
	
	protected void updateLayoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lid = request.getParameter("lid");
		String baitdate = request.getParameter("baitdate");
		String partid = request.getParameter("partid");
		String co = request.getParameter("count");
		String mainid = request.getParameter("mainid");
		String comp = request.getParameter("complete");
		String proid = request.getParameter("proid");
		String weight = request.getParameter("weight");
		String unit = request.getParameter("unit");
		String note = request.getParameter("note");
		String oldLid = request.getParameter("oldLid");
		
		Integer count = 0;
		Integer complete = 0;
		count = Integer.parseInt(co);
		complete = Integer.parseInt(comp);
		
		Layoff l = new Layoff(lid, baitdate, partid, count, mainid, complete, proid, weight, unit, note);
		
		int len = layoffService.updateLayoff(l, oldLid);
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void getAllLayoffByProId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proId = request.getParameter("proId");
		
		List<Layoff> list = layoffService.getAllLayoffByProId(proId);
		if(list.size() != 0) {
			Map<String, List<Layoff>> map = new HashMap<>();
			map.put("layoff", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	
	protected void updateLayoffState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String con = request.getParameter("complete");
		String lid = request.getParameter("lid");
		
		Integer complete = Integer.parseInt(con);
		
		boolean isState = layoffService.updateLayoffState(lid, complete);
		
		if(isState) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void removeLayoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lid = request.getParameter("lid");
		
		boolean isRemove = layoffService.removeLayoff(lid);
		
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}
}

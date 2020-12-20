package org.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.Assembly;
import org.bean.DataJson;
import org.bean.MessageJson;
import org.service.AssemblyService;
import org.service.AssemblyServiceImpl;

import com.google.gson.Gson;

public class AssemblyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AssemblyService assemblyService = new AssemblyServiceImpl();
	private Gson gson = new Gson();
       
	protected void getAssembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		
		Assembly a = assemblyService.getAssembly(aid);
		Map<String, Assembly> map = new HashMap<>();
		map.put("assembly", a);
		response.getWriter().write(gson.toJson(new DataJson(0, map)));
	}
	
	protected void insertAssembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String secdate = request.getParameter("secdate");
		String partid = request.getParameter("partid");
		String mainid = request.getParameter("mainid");
		String con = request.getParameter("complete");
		String proid = request.getParameter("proid");
		String ho = request.getParameter("hole");
		String shotdate = request.getParameter("shotdate");
		String shotco = request.getParameter("shotcomp");
		String require = request.getParameter("require");
		
		Integer complete = Integer.parseInt(con);
		Integer hole = Integer.parseInt(ho);
		Integer shotcomp = Integer.parseInt(shotco);
		
		Assembly a = new Assembly(aid, secdate, partid, complete, require, proid, mainid, hole, shotdate, shotcomp);
		int len = assemblyService.insertAssembly(a);
		
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "添加成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "添加失败!")));
		}
	}
	
	protected void updateAssembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String secdate = request.getParameter("secdate");
		String partid = request.getParameter("partid");
		String mainid = request.getParameter("mainid");
		String con = request.getParameter("complete");
		String proid = request.getParameter("proid");
		String ho = request.getParameter("hole");
		String shotdate = request.getParameter("shotdate");
		String shotco = request.getParameter("shotcomp");
		String require = request.getParameter("require");
		String oldAid = request.getParameter("oldAid");
		
		Integer complete = Integer.parseInt(con);
		Integer hole = Integer.parseInt(ho);
		Integer shotcomp = Integer.parseInt(shotco);
		
		Assembly a = new Assembly(aid, secdate, partid, complete, require, proid, mainid, hole, shotdate, shotcomp);
		int len = assemblyService.updateAssembly(a, oldAid);
		
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "编号重复!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void getAllAssemblyByProId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proid = request.getParameter("proid");
		
		List<Assembly> list = assemblyService.getAllAssemblyByProId(proid);
		
		if(list.size() != 0) {
			Map<String, List<Assembly>> map = new HashMap<>();
			map.put("assemblies", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void updateAssemblyState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String comp = request.getParameter("complete");
		
		Integer complete = Integer.parseInt(comp);
		
		boolean isUpdate = assemblyService.updateAssemblyState(aid, complete);
		if(isUpdate) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void updateHoleState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String shot = request.getParameter("shotcomp");
		
		Integer shotcomp = Integer.parseInt(shot);
		
		boolean isUpdate = assemblyService.updateHoleState(aid, shotcomp);
		if(isUpdate) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "修改成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "修改失败!")));
		}
	}
	
	protected void removeAssembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		
		boolean isRemove = assemblyService.removeAssembly(aid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "删除成功!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "删除失败!")));
		}
	}
	
}

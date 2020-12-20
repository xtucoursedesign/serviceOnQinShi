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
import org.bean.Preassembly;
import org.service.PreassemblyService;
import org.service.PreassemblyServiceImpl;

import com.google.gson.Gson;

public class PreassemblyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PreassemblyService preassemblyService = new PreassemblyServiceImpl();
	private Gson gson = new Gson();
       
	protected void getPreassembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paid = request.getParameter("paid");
		
		Preassembly p = preassemblyService.getPreassembly(paid);
		Map<String, Preassembly> map = new HashMap<>();
		
		map.put("preassembly", p);
		
		response.getWriter().write(gson.toJson(new DataJson(0, map)));
	}
	
	protected void getAllPreassemblyByProId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proid = request.getParameter("proid");
		
		List<Preassembly> list = preassemblyService.getAllPreassemblyByProId(proid);
		
		if(list.size() != 0) {
			Map<String, List<Preassembly>> map = new HashMap<>();
			map.put("preassemblies", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
		}
	}
	
	protected void insertPreassembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paid = request.getParameter("paid");
		String assdate = request.getParameter("assdate");
		String mainid = request.getParameter("mainid");
		String com = request.getParameter("complete");
		String require = request.getParameter("require");
		String proid = request.getParameter("proid");
		
		Integer complete = Integer.parseInt(com);
		
		Preassembly p = new Preassembly(paid, assdate, mainid, complete, require, proid);
		
		int len = preassemblyService.insertPreassembly(p);
		
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "��ӳɹ�!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "����ظ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "���ʧ��!")));
		}
	}
	
	protected void updatePreassembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paid = request.getParameter("paid");
		String assdate = request.getParameter("assdate");
		String mainid = request.getParameter("mainid");
		String com = request.getParameter("complete");
		String require = request.getParameter("require");
		String proid = request.getParameter("proid");
		
		String oldPaid = request.getParameter("oldPaid");
		
		Integer complete = Integer.parseInt(com);
		
		Preassembly p = new Preassembly(paid, assdate, mainid, complete, require, proid);
		
		int len = preassemblyService.updatePreassembly(p, oldPaid);
		
		if(len > 0) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "�޸ĳɹ�!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "����ظ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "�޸�ʧ��!")));
		}
	}
	
	protected void updatePreassemblyState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paid = request.getParameter("paid");
		String com = request.getParameter("complete");
		
		Integer complete = Integer.parseInt(com);
		
		boolean isUpdate = preassemblyService.updatePreassemblyState(paid, complete);
		
		if(isUpdate) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "�޸ĳɹ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "�޸�ʧ��!")));
		}
	}
	
	protected void removePreassembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paid = request.getParameter("paid");
		
		boolean isRemove = preassemblyService.removePreassembly(paid);
		
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "ɾ���ɹ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "ɾ��ʧ��!")));
		}
	}

}

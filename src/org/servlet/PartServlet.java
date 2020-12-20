package org.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(1, "ҳ�����!")));
			return;
		}
		
		Page<Part> p = partService.getPartByPage(pNo);
		List<Part> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Part>> map = new HashMap<>();
			map.put("part", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(1, "ҳ�����!")));
			return;
		}
		
		Page<Part> p = partService.getPartBySearch(pNo, searchType, searchKeyWord);
		List<Part> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Part>> map = new HashMap<>();
			map.put("part", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
		}
	}
	
	protected void removePart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		boolean isRemove = partService.removePart(pid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "ɾ���ɹ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "ɾ��ʧ��!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(0, "��ӳɹ�!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "����ظ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "���ʧ��!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(0, "�޸ĳɹ�!")));
		}else if(len == -1){
			response.getWriter().write(gson.toJson(new MessageJson(1, "����ظ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "�޸�ʧ��!")));
		}
	}
	
	protected void exportAllPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header[] = {"������", "�������", "�������", "������", "�������", "�������"};
		List<Part> list = partService.getAllPart();
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();//����Excel�ļ�(Workbook)
		HSSFSheet sheet = workbook.createSheet();//����������(Sheet)
        sheet.setDefaultColumnWidth((short) 18);
        HSSFCellStyle styleTitle = workbook.createCellStyle();
        styleTitle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        styleTitle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        styleTitle.setFillPattern(FillPatternType.LEAST_DOTS);
        HSSFRow row = sheet.createRow(0);
        
        for(int i = 0; i < header.length; i++) {
        	HSSFCell cell = row.createCell(i);
        	HSSFRichTextString text = new HSSFRichTextString(header[i]);
        	cell.setCellValue(text);
        	cell.setCellStyle(styleTitle);
        }
        
        for(int i = 0; i< list.size(); i++) {
        	Part p = list.get(i);
        	List<String> modal = new ArrayList<>();
        	modal.add(p.getPid());
        	modal.add(p.getName());
        	modal.add(p.getMaterial());
        	modal.add(p.getSpecifi());
        	modal.add(p.getTexture());
        	modal.add(p.getWeight() + p.getUnit());
        	row = sheet.createRow(i+1);
            for(int j = 0; j < modal.size(); j++) {
            	HSSFCell cell = row.createCell(j);
            	HSSFRichTextString text = new HSSFRichTextString(modal.get(j));
            	cell.setCellValue(text);
            }
        }
        
        response.setHeader("Content-disposition", "attachment;filename="+"Part.xls");
        // ���ļ�д�������
        workbook.write(response.getOutputStream());
	}
}

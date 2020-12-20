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
			response.getWriter().write(gson.toJson(new MessageJson(1, "ҳ�����!")));
			return;
		}
		
		Page<Main> p = mainService.getMainByPage(pNo);
		List<Main> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Main>> map = new HashMap<>();
			map.put("main", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(1, "ҳ�����!")));
			return;
		}
		
		Page<Main> p = mainService.getMainBySearch(pNo, searchType, searchKeyWord);
		List<Main> list = p.getList();
		if(list.size() != 0) {
			Map<String, Page<Main>> map = new HashMap<>();
			map.put("main", p);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
		}
	}
	
	protected void removeMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		boolean isRemove = mainService.removeMain(mid);
		if(isRemove) {
			response.getWriter().write(gson.toJson(new MessageJson(0, "ɾ���ɹ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "ɾ��ʧ��!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(0, "��ӳɹ�!")));
		}else if(len == -1) {
			response.getWriter().write(gson.toJson(new MessageJson(1, "����ظ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "���ʧ��!")));
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
			response.getWriter().write(gson.toJson(new MessageJson(0, "�޸ĳɹ�!")));
		}else if(len == -1){
			response.getWriter().write(gson.toJson(new MessageJson(1, "����ظ�!")));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(1, "�޸�ʧ��!")));
		}
	}
	
	protected void getAllMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Main> list = mainService.getAllMain();
		if(list.size() != 0) {
			Map<String, List<Main>> map = new HashMap<>();
			map.put("main", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "��������!")));
		}
	}
	
	protected void exportAllMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header[] = {"���ֹ����", "���ֹ�����", "���ֹ�����", "���ֹ����", "���ֹ�����", "���ֹ�����"};
		List<Main> list = mainService.getAllMain();
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();//����Excel�ļ�(Workbook)
		HSSFSheet sheet = workbook.createSheet();//����������(Sheet)
		// �����п�
        sheet.setDefaultColumnWidth((short) 18);
        // ���ñ�ͷ��ʽ
        HSSFCellStyle styleTitle = workbook.createCellStyle();
        // ����ǰ��ɫ
        styleTitle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        // ���ñ���ɫ
        styleTitle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        // ���ģʽ
        styleTitle.setFillPattern(FillPatternType.LEAST_DOTS);
        // ������ͷ����
        HSSFRow row = sheet.createRow(0);
        for(int i = 0; i < header.length; i++) {
        	// ������Ԫ�����
        	HSSFCell cell = row.createCell(i);
        	// ���ı�����ת��Ϊ���ı���Ϣ
        	HSSFRichTextString text = new HSSFRichTextString(header[i]);
        	// ��Ԫ��ֵ
        	cell.setCellValue(text);
        	cell.setCellStyle(styleTitle);
        }
//        System.out.println(list);
        for(int i = 0; i < list.size(); i++) {
        	Main sm = list.get(i);
        	List<String> modal = new ArrayList<>();
        	modal.add(sm.getMid());
        	modal.add(sm.getName());
        	modal.add(sm.getMaterial());
        	modal.add(sm.getSpecifi());
        	modal.add(sm.getTexture());
        	modal.add(sm.getWeight() + sm.getUnit());
        	
        	//�ӵڶ��п�ʼ�������
            row = sheet.createRow(i+1);
            for(int j = 0; j < modal.size(); j++) {
            	// ������Ԫ�����
            	HSSFCell cell = row.createCell(j);
            	// ���ı�����ת��Ϊ���ı���Ϣ
            	HSSFRichTextString text = new HSSFRichTextString(modal.get(j));
            	// ��Ԫ��ֵ
            	cell.setCellValue(text);
            }
        }
        response.setHeader("Content-disposition", "attachment;filename="+"Main.xls");//Excel�ļ���
        // ���ļ�д�������
        workbook.write(response.getOutputStream());
	}

}

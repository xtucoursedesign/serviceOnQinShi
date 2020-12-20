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
	
	protected void getAllMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Main> list = mainService.getAllMain();
		if(list.size() != 0) {
			Map<String, List<Main>> map = new HashMap<>();
			map.put("main", list);
			response.getWriter().write(gson.toJson(new DataJson(0, map)));
		}else {
			response.getWriter().write(gson.toJson(new MessageJson(2, "暂无数据!")));
		}
	}
	
	protected void exportAllMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header[] = {"主钢构编号", "主钢构名字", "主钢构材料", "主钢构规格", "主钢构材质", "主钢构重量"};
		List<Main> list = mainService.getAllMain();
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
		HSSFSheet sheet = workbook.createSheet();//创建工作表(Sheet)
		// 设置列宽
        sheet.setDefaultColumnWidth((short) 18);
        // 设置表头样式
        HSSFCellStyle styleTitle = workbook.createCellStyle();
        // 设置前景色
        styleTitle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        // 设置背景色
        styleTitle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        // 填充模式
        styleTitle.setFillPattern(FillPatternType.LEAST_DOTS);
        // 创建表头对象
        HSSFRow row = sheet.createRow(0);
        for(int i = 0; i < header.length; i++) {
        	// 创建单元格对象
        	HSSFCell cell = row.createCell(i);
        	// 将文本内容转换为富文本信息
        	HSSFRichTextString text = new HSSFRichTextString(header[i]);
        	// 单元格赋值
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
        	
        	//从第二行开始填充数据
            row = sheet.createRow(i+1);
            for(int j = 0; j < modal.size(); j++) {
            	// 创建单元格对象
            	HSSFCell cell = row.createCell(j);
            	// 将文本内容转换为富文本信息
            	HSSFRichTextString text = new HSSFRichTextString(modal.get(j));
            	// 单元格赋值
            	cell.setCellValue(text);
            }
        }
        response.setHeader("Content-disposition", "attachment;filename="+"Main.xls");//Excel文件名
        // 将文件写入输出流
        workbook.write(response.getOutputStream());
	}

}

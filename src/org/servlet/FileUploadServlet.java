package org.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bean.DataJson;
import org.bean.Image;
import org.bean.MessageJson;

import com.google.gson.Gson;


public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String upLoadPath;
	static {
		Properties pro = new Properties();
		try {
			pro.load(FileUploadServlet.class.getClassLoader().getResourceAsStream("upload.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		upLoadPath = pro.getProperty("uploadPath");
	}
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(gson.toJson(new MessageJson(1, "��ʹ��post����ʽ!")));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������
		DiskFileItemFactory dff = new DiskFileItemFactory();
		// ����ServletFileUpload����
		ServletFileUpload sfu = new ServletFileUpload(dff);
		// ���õ����ļ���С���ܳ���10m
		sfu.setFileSizeMax(1024 * 1024 * 10);

		List<FileItem> list = null;
		try {
			// ��������
			list = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		for(FileItem f : list) {
			if(f.isFormField() == false) {
				// ʹ��uuid��������
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String filePath = upLoadPath + "/" + uuid + f.getName();
				// д��
				File file = new File(filePath);
				try {
					f.write(file);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				
				Image img = new Image(UUID.randomUUID().toString().replaceAll("-", ""), f.getName(), "done", "/image/" + uuid + f.getName());
				Map<String, Image> map = new HashMap<String, Image>();
				map.put("img", img);
				response.getWriter().write(gson.toJson(new DataJson(0, map)));
			}
		}	
	}
}

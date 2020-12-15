package org.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html;charset=UTF-8");

        //获取文件名
        String fileName = request.getParameter("fileName");
        //通过fileName获取该文件的真实路径
        String realPath = this.getServletContext().getRealPath("/WEB-INF/download");
        String downloadPath = realPath+"/"+fileName;
        System.out.println(downloadPath);

        //设置浏览器响应体文件类型
        String mimeType = request.getServletContext().getMimeType(fileName);
        response.setContentType(mimeType);
        //解决文件名中文乱码问题
        String header = request.getHeader("User-Agent");
        if(header != null && header.contains("Firefox")) {
            fileName = "=?utf-8?B?"+new BASE64Encoder().encode(fileName.getBytes("utf-8"))+"?=";
        }else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        //设置浏览器响应体内容格式，为附件格式。(告诉浏览器别播放，下载)
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);

        //读取目标资源，同时写到客户端（下载）
        //创建读入流
        FileInputStream fis = new FileInputStream(downloadPath);
        //创建写出流
        ServletOutputStream ops = response.getOutputStream();
        int len =  0;
        byte[] b = new byte[1024];
        while((len = fis.read(b)) != -1) {
            ops.write(b, 0, len);
        }

        ops.close();
        fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

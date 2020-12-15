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

        //��ȡ�ļ���
        String fileName = request.getParameter("fileName");
        //ͨ��fileName��ȡ���ļ�����ʵ·��
        String realPath = this.getServletContext().getRealPath("/WEB-INF/download");
        String downloadPath = realPath+"/"+fileName;
        System.out.println(downloadPath);

        //�����������Ӧ���ļ�����
        String mimeType = request.getServletContext().getMimeType(fileName);
        response.setContentType(mimeType);
        //����ļ���������������
        String header = request.getHeader("User-Agent");
        if(header != null && header.contains("Firefox")) {
            fileName = "=?utf-8?B?"+new BASE64Encoder().encode(fileName.getBytes("utf-8"))+"?=";
        }else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        //�����������Ӧ�����ݸ�ʽ��Ϊ������ʽ��(����������𲥷ţ�����)
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);

        //��ȡĿ����Դ��ͬʱд���ͻ��ˣ����أ�
        //����������
        FileInputStream fis = new FileInputStream(downloadPath);
        //����д����
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

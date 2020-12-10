package org.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.DataJson;

import com.google.gson.Gson;

public class AddrServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	protected void address(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String ipAddr = request.getRemoteAddr();
		Map<String,String> map = new HashMap<String,String>();
		map.put("location", post(ipAddr));
		response.getWriter().write(gson.toJson(new DataJson(0, map)));
	}
	
	public static String post(String ip) throws IOException{
//		System.out.println(ip);
		if("127.0.0.1".equals(ip)) {
			return "����";
		}
		// �����ַ
		String uri = "http://whois.pconline.com.cn/ipJson.jsp?ip=";
		uri += ip;
		// ���ݵ�ַ����url����
		URL url = new URL(uri);
		// ����url���������
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		// ��������ʽ
		urlConn.setRequestMethod("GET");
		// urlConn.setRequestMethod("POST");
		// ��������ʱ�ĳ�ʱʱ��
		urlConn.setReadTimeout(5000);
		urlConn.setConnectTimeout(5000);
		// ��������ͷ
		urlConn.setRequestProperty("Connection", "keep-alive");
		urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		urlConn.setRequestProperty("Content-Length", "10");
		// ���������������Ϊ������������� // InputĬ����true
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.connect();
		
		if(urlConn.getResponseCode() == 200){
			// ��ȡ��Ӧ����������
			InputStream is = urlConn.getInputStream();
			// �����ֽ������������
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// �����ȡ�ĳ���
			int len = 0;
			// ���建����
			byte[] buffer = new byte[1024];
			while((len = is.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			// �ر���
			is.close();
			baos.close();
			// �����ַ��� ָ���ַ���
			String result = new String(baos.toByteArray(), "gbk");
			String[] arr = result.split(",");
			String loca = null;
			try {
				for(String s : arr) {
					System.out.println(s);
				}
				String st = arr[3];
				System.out.println(st);
				loca = st.split(":")[1];
				loca = loca.replaceAll("\"", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return loca;
			
		}else{
			System.out.println("����ʧ�ܣ�");
			return "����";
		}
	}
}

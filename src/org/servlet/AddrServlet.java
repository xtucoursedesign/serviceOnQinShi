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
			return "北京";
		}
		// 请求地址
		String uri = "http://whois.pconline.com.cn/ipJson.jsp?ip=";
		uri += ip;
		// 根据地址创建url对象
		URL url = new URL(uri);
		// 根据url对象打开链接
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		// 设置请求方式
		urlConn.setRequestMethod("GET");
		// urlConn.setRequestMethod("POST");
		// 设置请求超时的超时时间
		urlConn.setReadTimeout(5000);
		urlConn.setConnectTimeout(5000);
		// 设置请求头
		urlConn.setRequestProperty("Connection", "keep-alive");
		urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		urlConn.setRequestProperty("Content-Length", "10");
		// 发送请求必须设置为允许输出和输入 // Input默认是true
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.connect();
		
		if(urlConn.getResponseCode() == 200){
			// 获取响应输入流对象
			InputStream is = urlConn.getInputStream();
			// 创建字节流输出流对象
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 定义读取的长度
			int len = 0;
			// 定义缓冲区
			byte[] buffer = new byte[1024];
			while((len = is.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			// 关闭流
			is.close();
			baos.close();
			// 返回字符串 指定字符集
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
			System.out.println("请求失败！");
			return "北京";
		}
	}
}

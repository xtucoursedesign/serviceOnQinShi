package org.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bean.MessageJson;

import com.google.gson.Gson;

// 除了登录，其他请求都要进行检查token
public class CheckLoginFilter extends HttpFilter implements Filter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
		String method = request.getParameter("method");
		String token = request.getParameter("token");
		String sessionToken = (String)request.getSession().getAttribute("token");
//		System.out.println("sessionToken=" + sessionToken + " token=" + token);
		if("login".equals(method)) {
			chain.doFilter(request, response);
		}else if(token != null && token.equals(sessionToken)) {
			chain.doFilter(request, response);
		}else {
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(new MessageJson(401, "请重新登录!")));
		}
	}

}
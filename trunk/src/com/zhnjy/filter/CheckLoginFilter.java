package com.zhnjy.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhnjy.entity.User;

public class CheckLoginFilter implements Filter{
	protected FilterConfig filterConfig = null;   
    private String redirectURL = null;   
    private String noFilterUrlParams; 
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest request = (HttpServletRequest) req;   
		  HttpServletResponse response = (HttpServletResponse) res;  
		  
		  String path = request.getServletPath();

		if (check(path)) {// 路径需要检查
			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute("user");
			
			if(user == null){
				res.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("parent.location.href='" + redirectURL + "'; ");// 跳转到指定页面
				out.print("</script>");
				out.close();
				return;
			}
			
		}
		
		chain.doFilter(request, response);		
	}

	public void init(FilterConfig filterConfig ) throws ServletException {
		this.filterConfig = filterConfig;   
		redirectURL = filterConfig.getInitParameter("redirectURL");
		noFilterUrlParams = filterConfig.getInitParameter("noFilterUrlParams");

		   
	}
	
	
	/**
	* 查询url是否需要检查
	* 
	* @param path
	* @return false:不需要过滤；true:需要过滤
	*/
	private boolean check(String path) {
		boolean reBoolean = true;
		String urls[] = noFilterUrlParams.split(";");
		String packges[]=path.split("/");
		for(String packge : packges){
			
		if( packge.equals("css") || packge.equals("easyui") || packge.equals("file") || packge.equals("images") || packge.equals("jBox")|| packge.equals("js") || packge.equals("show_data_jsp") ){
			reBoolean = false;
				break;
			}
		}
				
		if(reBoolean){
			for (String url : urls) {
				if (url.indexOf(path) == 0) {// 当前的url出现在配置url里时
					reBoolean = false;
					break;
				}
			}
		}
		return reBoolean;
	}
}

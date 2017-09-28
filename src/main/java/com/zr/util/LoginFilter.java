package com.zr.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author zhang
 *
 */
/*@WebFilter(value = "/node.jsp", initParams = {
		@WebInitParam(name = "notCheckURLList", value = ""),
		@WebInitParam(name = "redirectURL", value = "/login.jsp"), })*/
public class LoginFilter implements Filter {  
  
	protected FilterConfig filterConfig = null; 
	 private String redirectURL = null; 
	 private List notCheckURLList = new ArrayList(); 
	 private String sessionKey = null;
	 
	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException 
	 { 
		 System.out.println(222);
	  HttpServletRequest request = (HttpServletRequest) servletRequest; 
	  HttpServletResponse response = (HttpServletResponse) servletResponse;

	  HttpSession session = request.getSession(); 
	  sessionKey = (String)session.getAttribute("uname");
	  System.out.println(sessionKey);
	 
	  if(sessionKey != null) 
	  { 
	   filterChain.doFilter(request, response); 
	   return; 
	  } 
	  if((!checkRequestURIIntNotFilterList(request)) && session.getAttribute("uname") == null) 
	  { 
	   response.sendRedirect(request.getContextPath() + redirectURL); 
	   return; 
	  } 
	  filterChain.doFilter(servletRequest, servletResponse); 
	 }

	 public void destroy() 
	 { 
	  notCheckURLList.clear(); 
	 }

	 private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) 
	 { 
	  String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo()); 
	  return notCheckURLList.contains(uri); 
	 }

	 public void init(FilterConfig filterConfig) throws ServletException 
	 { 
	  this.filterConfig = filterConfig; 
	  redirectURL = filterConfig.getInitParameter("redirectURL"); 	 
	 
	  String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
	  if(notCheckURLListStr != null) 
	  { 
	   StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";"); 
	   notCheckURLList.clear(); 
	   while(st.hasMoreTokens()) 
	   { 
	    notCheckURLList.add(st.nextToken()); 
	   } 
	   
	  } 
	  
	  System.out.println(111);
	 } 
	}
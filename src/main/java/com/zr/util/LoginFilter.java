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

@WebFilter(value = "*.jsp", initParams = { @WebInitParam(name = "checkSessionKey", value = "user"),
		@WebInitParam(name = "notCheckURLList", value = "/userselectSub.jsp;/index.jsp;/login.jsp;/index_content.jsp"),
		@WebInitParam(name = "redirectURL", value = "/login.jsp"), })
public class LoginFilter implements Filter {

	protected FilterConfig filterConfig = null;
	private String redirectURL = null;
	private List<String> notCheckURLList = new ArrayList<String>();
	private String sessionKey = null;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();

		if (sessionKey != null) {
			filterChain.doFilter(request, response);
			return;
		}

		if ((!checkRequestURIIntNotFilterList(request)) && session.getAttribute(sessionKey) == null) {
			response.sendRedirect(request.getContextPath() + redirectURL);
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);

	}

	public void destroy() {
		notCheckURLList.clear();
	}

	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
		return notCheckURLList.contains(uri);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter begin");
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey = filterConfig.getInitParameter("checkSessionKey");
		String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken());
			}
		}
	}
}
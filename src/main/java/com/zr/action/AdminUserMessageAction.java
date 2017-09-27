package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.UserService;
import com.zr.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminUserMessageAction
 */
@WebServlet("/AdminUserMessage")
public class AdminUserMessageAction extends HttpServlet {
	
	UserService us = new UserServiceImpl();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserMessageAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		JSONObject j = new JSONObject();
		j = us.getUsersByPageAndPagesize(page, pageSize);

		PrintWriter pw = response.getWriter();
		pw.write(j.toString());
		System.out.println(j);
	}

}

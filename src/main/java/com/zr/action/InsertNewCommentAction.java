package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.zr.service.CommentService;
import com.zr.service.impl.CommentServiceImpl;

@WebServlet("/InsertNewCommentAction")
public class InsertNewCommentAction extends HttpServlet {
	CommentService cService = new CommentServiceImpl();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int sid = Integer.parseInt(request.getParameter("sid"));
		int uid = (int) request.getSession().getAttribute("userID");
		// int uid = 2;
		String comText = request.getParameter("comText");
		boolean b = cService.insertComment(sid, uid, comText);
		PrintWriter out = response.getWriter();
		if (b == true) {
			out.write("success");
		} else {
			out.write("fail");
		}
	}
}

package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.CommentService;
import com.zr.service.impl.CommentServiceImpl;

@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentService cService = new CommentServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String[] s = request.getParameterValues("selectedIDs[]");
		int[] in = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			in[i] = Integer.parseInt(s[i]);
		}
		boolean b = cService.deleteCommentByCIDArray(in);
		PrintWriter out = response.getWriter();
		if (b == true) {
			out.write("success");
		} else {
			out.write("DeleteUsers fail");
		}
	}

}

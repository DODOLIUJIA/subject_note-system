package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.Comment;
import com.zr.service.CommentService;
import com.zr.service.impl.CommentServiceImpl;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetAllCommentBySID
 */
@WebServlet("/GetAllCommentBySID")
public class GetAllCommentBySID extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CommentService cService = new CommentServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int sid = Integer.parseInt(request.getParameter("sid"));
		List<Comment> list = cService.getCommentBySubID(sid);
		JSONArray json=JSONArray.fromObject(list);
		System.out.println(json.toString());
		out.write(json.toString());

	}

}

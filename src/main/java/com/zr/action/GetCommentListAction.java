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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetCommentListAction
 */
@WebServlet("/GetCommentListAction")
public class GetCommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentService cService=new CommentServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int  page =  Integer.parseInt(request.getParameter("page"));
		int  pageSize = Integer.parseInt(request.getParameter("rows"));
		List<Comment> list=cService.getCommentsByLimit((page-1)*pageSize, pageSize);
		JSONObject json=new JSONObject();
		json.put("total", cService.getCommentsSum());
		json.put("rows", list);
		System.out.println(json.toString());
		PrintWriter out=response.getWriter();
		out.write(json.toString());
	}

}

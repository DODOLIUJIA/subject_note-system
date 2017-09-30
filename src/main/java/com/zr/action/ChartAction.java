package com.zr.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.CommentService;
import com.zr.service.SubLabelService;
import com.zr.service.impl.CommentServiceImpl;
import com.zr.service.impl.SubLabelServiceImpl;

import net.sf.json.JSONArray;

@WebServlet("/chart")
public class ChartAction extends HttpServlet {
	SubLabelService sub_label = new SubLabelServiceImpl();
	CommentService cs = new CommentServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONArray result = sub_label.getSubLabelsCount();
		response.getWriter().write(result.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

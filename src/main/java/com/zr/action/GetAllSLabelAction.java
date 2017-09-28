package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.SubLabelService;
import com.zr.service.impl.SubLabelServiceImpl;

@WebServlet("/GetAllSLabelAction")
public class GetAllSLabelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SubLabelService sService=new SubLabelServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String s=sService.getAllLabelValueAndText().toString();
		System.out.println(s);
		out.write(s);
	}

}

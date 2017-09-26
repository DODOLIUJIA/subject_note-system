package com.zr.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.util.Ocr;


/**
 * Servlet implementation class AnalyzeImg
 */
@WebServlet("/AnalyzeImg")
public class AnalyzeImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String imgPath = (String) request.getParameter("imgPath");
		String result = "";
		try {
			Ocr ocr = new Ocr(request.getServletContext().getRealPath(""));
			result = ocr.getResult(imgPath);
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

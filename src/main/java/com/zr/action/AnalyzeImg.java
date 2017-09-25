package com.zr.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.util.OCR;

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
		StringBuilder valCode = null;
		try {
			OCR ocr = new OCR();
			ocr.setBasePath("D:\\EclipseWS\\sub_note\\");
			valCode = ocr.recognizeText(new File(imgPath), "jpg");
			
			System.out.println(valCode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().write(valCode.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

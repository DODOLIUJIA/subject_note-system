package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;

import net.sf.json.JSONObject;

/**
 * 閫氳繃棰樼洰鐨刬d鏉ヨ幏鍙栭鐩殑鎵�湁淇℃伅
 * @author JACK
 *
 * Servlet implementation class GetSubjectBySId
 */
@WebServlet("/GetSubjectBySId")
public class GetSubjectBySId extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	SubService sService=new SubServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		if(request.getParameter("sid") == null)
			return;
		int sid=Integer.parseInt(request.getParameter("sid"));
		PrintWriter out=response.getWriter();
		JSONObject json = sService.getSubjectBySid(sid);
		//System.out.println("getSubjectBySId涓殑杈撳嚭锛�+json.toString());
		out.write(json.toString());
	}

}

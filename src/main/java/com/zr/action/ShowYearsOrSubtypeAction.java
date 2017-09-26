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

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ShowYears
 */
@WebServlet("/ShowYearsAndSubtype")
public class ShowYearsOrSubtypeAction extends HttpServlet {
	SubService subs = new SubServiceImpl();
	
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowYearsOrSubtypeAction() {
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
		PrintWriter pw = response.getWriter();
		String type = request.getParameter("type");
		if("year".equals(type)){
			JSONArray ja = subs.getAllYears();
			pw.write(ja.toString());
		}else if("subtype".equals("subtype")) {
			JSONArray ja = subs.getAllSubType();
			pw.write(ja.toString());
			System.out.println(ja);
		}
	}

}

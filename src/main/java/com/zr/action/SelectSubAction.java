package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectSubAction
 */
@WebServlet("/selectsub")
public class SelectSubAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectSubAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sublabel = request.getParameter("sublabel");
		String subCrateTime = request.getParameter("subCrateTime");
		String STcheck = request.getParameter("STcheck");
		String SCTcheck = request.getParameter("SCTcheck");
		HttpSession session = request.getSession();
		session.setAttribute("sublabel", sublabel);
		session.setAttribute("subCrateTime", subCrateTime);
		session.setAttribute("STcheck", STcheck);
		session.setAttribute("SCTcheck", SCTcheck);
		System.out.println(sublabel);
		JSONObject j = new JSONObject();
		PrintWriter pw = response.getWriter();
		pw.write(j.toString());
	}

}

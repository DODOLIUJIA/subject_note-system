package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.model.Subject;
import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;

import net.sf.json.JSONObject;

/**
 * 吴尚鑫
 * Servlet implementation class SelectSubAction
 */
@WebServlet("/getSubMsg")
public class GetSubMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 SubService sbs = new SubServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSubMsg() {
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
		HttpSession session = request.getSession();
		String sublabel = request.getParameter("sublabel");
		int subCrateTime= Integer.parseInt(request.getParameter("subCrateTime"));
		
		session.setAttribute("sublabel", sublabel);
		session.setAttribute("subCrateTime", subCrateTime);
		System.out.println("subCrateTime = "+subCrateTime);
		JSONObject j = new JSONObject();
		PrintWriter pw = response.getWriter();
		pw.write(j.toString());
	}

}

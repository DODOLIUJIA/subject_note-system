package com.zr.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.service.NoteService;
import com.zr.service.impl.NoteServiceimpl;

/**
 * Servlet implementation class ShowAction
 */
@WebServlet("/show")
public class ShowAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   NoteService ns = new NoteServiceimpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lid = request.getParameter("id");
		int n_lid = Integer.parseInt("lid");
		String lname = ns.getN_lname(n_lid);
		HttpSession session = request.getSession();
		session.setAttribute("n_lname", lname);
	}

}

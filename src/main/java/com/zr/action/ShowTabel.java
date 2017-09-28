package com.zr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.model.N_label;
import com.zr.service.NoteService;
import com.zr.service.impl.NoteServiceimpl;

/**
 * Servlet implementation class ShowTabel
 */
@WebServlet("/showtabel")
public class ShowTabel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoteService ns = new NoteServiceimpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session = request.getSession();

		if (session.getAttribute("userId") != null) {
			int userid = (int) session.getAttribute("userId");
			List<N_label> notelabels = ns.getNotetabel(userid);
			session.setAttribute("notelabels", notelabels);
		}

		response.sendRedirect("note.jsp");
	}

}

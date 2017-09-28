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


@WebServlet("/looknote")
public class LookNoteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   NoteService ns = new  NoteServiceimpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("id");
	    int noteid = Integer.parseInt(id);
	   String text =  ns.getNotetext(noteid);
	   String title = ns.getNotetitle(noteid);
	   HttpSession session = request.getSession();
	   session.setAttribute("noteid", noteid);
	   session.setAttribute("title", title);
	   session.setAttribute("text", text);
	   response.sendRedirect("looknote.jsp");
	   //request.getRequestDispatcher("looknote.jsp").forward(request, response);
	}

}

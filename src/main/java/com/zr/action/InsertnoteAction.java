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

import com.zr.model.Note;
import com.zr.service.NoteService;
import com.zr.service.impl.NoteServiceimpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InsertnoteAction
 */
@WebServlet("/insertnote")
public class InsertnoteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   NoteService ns = new NoteServiceimpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     int noteid = 0;
		String notetitle = request.getParameter("title");
	      String notetext = request.getParameter("notetext");
	      String notesummary =request.getParameter("notesummary");
	      HttpSession session = request.getSession();
	     int userid =  (int) session.getAttribute("userId");
		  String lname = (String)session.getAttribute("n_lname");
	      if(""!=notetitle&&""!=notetext&&""!=notesummary){
	    	  noteid = ns.insertnote(userid, notetitle, notetext, notesummary);	
		      int q = ns.getN_lid(lname);
		      int n = ns.insertNote_lable(noteid, q);
	      }     
	      JSONObject json = new JSONObject();
	      PrintWriter pw = response.getWriter();
	      json.put("msg", noteid);
	      pw.write(json.toString());
	}

}

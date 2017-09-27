package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.service.NoteService;
import com.zr.service.impl.NoteServiceimpl;

import net.sf.json.JSONObject;


@WebServlet("/updatenote")
public class UpdateNoteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     NoteService ns = new NoteServiceimpl();  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session = request.getSession();
		int noteid = (int) session.getAttribute("noteid");
		String lname = request.getParameter("type");
		System.out.println(lname);
		String title = request.getParameter("title");
		String notetext = request.getParameter("notetext");
		//int n_lid = Integer.parseInt(lid);
		//int noteid = ns.getnoteid(title);
		int n_lid = ns.getlid(lname);
		int i = ns.updatelid(n_lid, noteid);
		int j = ns.updatetext(notetext,title, noteid);
		  JSONObject json = new JSONObject();
	      PrintWriter pw = response.getWriter();
	      json.put("msg", i);
	      json.put("msg1", j);
	      pw.write(json.toString());
	     // System.out.println(json.toString());
		
	}

}

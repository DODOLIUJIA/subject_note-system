package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author liujia
 *
 */

import com.zr.service.NoteService;
import com.zr.service.impl.NoteServiceimpl;

import net.sf.json.JSONObject;
@WebServlet("/deletenote")
public class DeleteNoteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    NoteService ns = new NoteServiceimpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf8");
		String notetitle = request.getParameter("title");
		int noteid = ns.getnoteid(notetitle);
		int i = ns.delectN_n_label(noteid);
		int j = ns.deleteNoteBynoteid(noteid);
		JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		 json.put("msg", i);
	     json.put("msg1", j);
	     pw.write(json.toString());
	}

}

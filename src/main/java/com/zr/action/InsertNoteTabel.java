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

/**
 * 
 * @author liujia
 *
 */
@WebServlet("/insertTabel")
public class InsertNoteTabel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    NoteService ns = new  NoteServiceimpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		int i =0;
		HttpSession session = request.getSession();
		int userid = (int)session.getAttribute("userId");
		String lname = request.getParameter("tabel");
		if(""!=lname){
			 i = ns.insertNoteTabel(lname,userid);
		}
		JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		json.put("msg", i);
		pw.write(json.toString());
		
	}

}

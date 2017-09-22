package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.Note;
import com.zr.service.NoteService;
import com.zr.service.impl.NoteServiceimpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 展现笔记
 * @author liujia
 *
 */
@WebServlet("/shownote")
public class ShownoteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    NoteService ns = new NoteServiceimpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(1111);
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		List<Note> notes = ns.getallnotesByn_lname("java");
		JSONArray json = JSONArray.fromObject(notes);
		 PrintWriter pw = response.getWriter();
		pw.write(json.toString());
	//	System.out.println(json.toString());
	}

}

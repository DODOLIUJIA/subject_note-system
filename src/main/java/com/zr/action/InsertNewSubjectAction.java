package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.dao.SubDao;
import com.zr.dao.impl.SubDaoImpl;
import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;

@WebServlet("/InsertNewSubjectAction")
public class InsertNewSubjectAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	SubService sService=new SubServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String content=request.getParameter("content");
		String summary=request.getParameter("sum");
		String answer=request.getParameter("ans");
		int type=Integer.parseInt(request.getParameter("type"));
		int time=Integer.parseInt(request.getParameter("time"));
		boolean b=sService.insertNewSub(summary, content, type, answer, time);
		if(b==true){
			out.write("Insert Success");
		}else{
			out.write("Insert Fail");
		}
	}

}

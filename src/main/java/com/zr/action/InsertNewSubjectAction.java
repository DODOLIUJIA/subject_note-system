package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.SubLabelService;
import com.zr.service.SubService;
import com.zr.service.impl.SubLabelServiceImpl;
import com.zr.service.impl.SubServiceImpl;

@WebServlet("/InsertNewSubjectAction")
public class InsertNewSubjectAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	SubService sService = new SubServiceImpl();
	SubLabelService slService = new SubLabelServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String content = request.getParameter("content");
		String summary = request.getParameter("sum");
		String answer = request.getParameter("ans");
		int type = Integer.parseInt(request.getParameter("type"));
		int time = Integer.parseInt(request.getParameter("time"));
		String[] L = request.getParameterValues("Lids[]");
		int[] lids = new int[L.length];
		for (int i = 0; i < L.length; i++) {
			lids[i] = Integer.parseInt(L[i]);
		}
		boolean b = sService.insertNewSub(summary, content, type, answer, time);
		int sid = sService.getSubIDBySummaryAndTextAndType(summary, content, type);
		boolean a = slService.insertNewSSLabel(sid, lids);
		slService.updateAllSubjectLabelCount();
		if (b == true && a == true) {
			out.write("Insert Success");
		} else {
			out.write("Insert Fail");
		}
	}

}

package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.Subject;
import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectAllSubjectAction
 */
@WebServlet("/selectAllSubject")
public class SelectAllSubjectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       SubService subS = new SubServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAllSubjectAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		int loadtimms = Integer.parseInt(request.getParameter("loadtimms"));
		List<Subject> Subs = subS.selectAllSub(loadtimms);
		JSONObject S = new JSONObject();
		PrintWriter pw = response.getWriter();
		S.put("Subs", Subs);
		pw.write(S.toString());
	}

}

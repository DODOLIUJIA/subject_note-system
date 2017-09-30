package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.model.Subject;
import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectSubAction
 */
@WebServlet("/selectSub")
public class SelectSubAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SubService sbs = new SubServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSubAction() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session = request.getSession();
		String sublabel = (String) session.getAttribute("sublabel");
		int subCrateTime = (int) session.getAttribute("subCrateTime");
		int loadtimms = Integer.parseInt(request.getParameter("loadtimms"));
		List<Subject> subs = new ArrayList<Subject>();
		subs = sbs.selectSubsByMsg(sublabel, subCrateTime, loadtimms);
		PrintWriter pw = response.getWriter();
		JSONObject j = new JSONObject();
		j.put("Subs", subs);
		pw.write(j.toString());
	}

}

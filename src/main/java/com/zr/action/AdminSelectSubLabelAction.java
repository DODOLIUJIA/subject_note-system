package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.SubLabelService;
import com.zr.service.impl.SubLabelServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminSelectSubLabelAction
 */
@WebServlet("/SelectSubLabel")
public class AdminSelectSubLabelAction extends HttpServlet {
	
	SubLabelService sls = new SubLabelServiceImpl();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSelectSubLabelAction() {
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
		int flag = Integer.parseInt(request.getParameter("flag"));
		JSONObject j = new JSONObject();
		if(flag == 0) {
			int page = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			
			j = sls.getSubLabelsByPageAndPagesize(page, pageSize);
			
		}else if(flag == 1) {
			String newLabName = request.getParameter("newln");
			j = sls.insertNewLabAndeExist(newLabName);
		}else if(flag == 2) {
			int s_lid = Integer.parseInt(request.getParameter("s_lid"));
			j = sls.deleteSubLabelByS_lid(s_lid);
		}else if(flag == 3) {
			String updataS_LabName = request.getParameter("uln");
			int s_lid = Integer.parseInt(request.getParameter("s_lid"));
			j = sls.updataS_LabAndeExist(updataS_LabName, s_lid);
		}
		PrintWriter pw = response.getWriter();
		pw.write(j.toString());
	}

}

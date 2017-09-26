package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectSub
 */
@WebServlet("/SelectSub")
public class AdminSelectSubjectAction extends HttpServlet {
	
	SubService subService = new SubServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSelectSubjectAction() {
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
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int yearInt = 0;
		String yearStr = request.getParameter("year");
		String subType = request.getParameter("subType");
		String subLabel = request.getParameter("subLabel");
		JSONObject j = new JSONObject();
		if("所有".equals(yearStr) && "所有".equals(subType) && "所有".equals(subLabel) ) {
			j = subService.getSubsByPageAndPagesize(page, pageSize);
		}else if("所有".equals(yearStr) && "所有".equals(subType) && subLabel != "所有"){
			j = subService.getSubsByPageAndPagesizeBySubLabel(page, pageSize, subLabel);

		}else if("所有".equals(yearStr) && subType != "所有" && "所有".equals(subLabel)){
			j = subService.getSubsByPageAndPagesizeBySubType(page, pageSize, subType);

		}else if("所有" != yearStr && "所有".equals(subType) && "所有".equals(subLabel)){
			yearInt = Integer.parseInt(yearStr);
			j = subService.getSubsByPageAndPagesizeBySubYear(page, pageSize, yearInt);

		}else if("所有".equals(yearStr) && subType != "所有" && subLabel != "所有"){
			j = subService.getSubsByPageAndPagesizeBySubTypeAndSubLabel(page, pageSize, subType, subLabel);

		}else if("所有" != yearStr && "所有".equals(subType) && subLabel != "所有"){
			yearInt = Integer.parseInt(yearStr);
			j = subService.getSubsByPageAndPagesizeByYearAndSubLabel(page, pageSize, yearInt, subLabel);

		}else if("所有" != yearStr && subType != "所有" && "所有".equals(subLabel)){
			yearInt = Integer.parseInt(yearStr);
			j = subService.getSubsByPageAndPagesizeByYearAndSubType(page, pageSize, yearInt, subType);
		}else{
			yearInt = Integer.parseInt(yearStr);
			j = subService.getSubsByPageAndPagesizeByYearAndSubTypeAndSubLabel(page, pageSize, yearInt, subType, subLabel);
		}
		PrintWriter pw = response.getWriter();
		pw.write(j.toString());
	}

}

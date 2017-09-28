package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.RoleManageService;
import com.zr.service.impl.RoleManageServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RoleManageAction
 */
@WebServlet("/roleManage")
public class RoleManageAction extends HttpServlet {
	
	RoleManageService rms = new RoleManageServiceImpl();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleManageAction() {
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
		String type = request.getParameter("type");
		JSONObject j = new JSONObject();
		if("user".equals(type)) {
			int page = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			j = rms.getUsersByPageAndPagesize(page, pageSize);
			PrintWriter pw = response.getWriter();
			pw.write(j.toString());
		}else if("role".equals(type)) {
			JSONArray roles = rms.getAllRole();
			PrintWriter pw = response.getWriter();
			pw.write(roles.toString());
		}else if("updata".equals(type)) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			j = rms.updataSuccessByUidAndRid(userId, roleId);
			PrintWriter pw = response.getWriter();
			pw.write(j.toString());
		}
	}

}

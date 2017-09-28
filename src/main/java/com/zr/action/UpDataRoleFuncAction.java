package com.zr.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.RoleService;
import com.zr.service.impl.RoleServiceImpl;

/**
 * Servlet implementation class UpDataRoleFuncAction
 */
@WebServlet("/upDataRoleFunc")
public class UpDataRoleFuncAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       RoleService rs = new RoleServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpDataRoleFuncAction() {
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
		String roleName = request.getParameter("roleName");
		String funcsid = request.getParameter("funcsid");
	    rs.UpdataRoleFunc(roleName, funcsid);
	}

}

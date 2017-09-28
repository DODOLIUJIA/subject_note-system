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

import com.zr.model.Role;
import com.zr.service.RoleService;
import com.zr.service.impl.RoleServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ShowRolesAction
 */
@WebServlet("/ShowRoles")
public class ShowRolesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       RoleService rs = new RoleServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRolesAction() {
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
		JSONArray j = rs.selectAllRole();
        PrintWriter pw = response.getWriter();
        pw.write(j.toString());
	}

}

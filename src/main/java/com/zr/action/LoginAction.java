package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zr.service.LoginService;
import com.zr.service.impl.LoginServiceImpl;
import net.sf.json.JSONObject;

/**
 * 
 * @author zhang
 *
 */
@WebServlet("/login")
public class LoginAction extends HttpServlet{

	private LoginService loginService = new LoginServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String password = req.getParameter("password");
		JSONObject result = loginService.selectUser(uname, password);
		HttpSession session = req.getSession();
		session.setAttribute("uname", uname);
		session.setAttribute("userId", result.get("userId"));
		
		resp.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		json.put("result",result);
		PrintWriter  pw =  resp.getWriter();
		pw.write(json.toString());
	}
}

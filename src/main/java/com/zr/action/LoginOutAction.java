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
@WebServlet(name="LoginOutAction",urlPatterns="/loginout")
public class LoginOutAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		session.removeAttribute("uname");
		
		resp.sendRedirect("index.jsp");
	}
}
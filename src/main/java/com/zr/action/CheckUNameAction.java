package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zr.service.RegisterService;
import com.zr.service.impl.RegisterServiceImpl;

import net.sf.json.JSONObject;
/**
 * 注解WebServlet用来描述一个Servlet
 * 属性name描述Servlet的名字,可选
 * 属性urlPatterns定义访问的URL,或者使用属性value定义访问的URL.(定义访问的URL是必选属性)
 */
@WebServlet(name="CheckUNameAction",urlPatterns="/checkUname")
public class CheckUNameAction extends HttpServlet{

	private RegisterService userService = new RegisterServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = req.getParameter("uname");
		int result = userService.checkUname(uname);
		
		resp.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		json.put("result",result);
		PrintWriter  pw =  resp.getWriter();
		pw.write(json.toString());
	}
}

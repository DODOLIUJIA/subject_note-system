package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.service.ManagerFuncService;
import com.zr.service.impl.ManagerFuncServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetManagerFuncsAvtion
 */
@WebServlet("/getManagerFuncs")
public class GetManagerFuncsAvtion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerFuncService mfs = new ManagerFuncServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetManagerFuncsAvtion() {
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
		HttpSession session = request.getSession();
		String uname = (String)session.getAttribute("uname");
		JSONArray funcs = mfs.selectFunctionsByUname("zzz", 0);
		JSONObject Func = new JSONObject();
		Func.put("menus", funcs);
		System.out.println(Func.toString());
		PrintWriter pw = response.getWriter();
		pw.write(Func.toString());
	}

}

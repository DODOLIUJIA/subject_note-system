package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.model.Subject;
import com.zr.model.SubjectLabel;
import com.zr.service.SubService;
import com.zr.service.impl.SubServiceImpl;


/**
 * Servlet implementation class ShowLabelAndTimeAction
 */
@WebServlet("/showLabelAndTime")
public class ShowLabelAndTimeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SubService sbs = new SubServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLabelAndTimeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SubjectLabel> labels = sbs.selectSubLabel();
		List<Subject> times = sbs.selectSubTime();
		HttpSession session = request.getSession();
		session.setAttribute("labels", labels);
		session.setAttribute("times", times);
		request.getRequestDispatcher("userselectSub.jsp").forward(request, response);
	}

}

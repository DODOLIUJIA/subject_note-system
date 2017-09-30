package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.CommentService;
import com.zr.service.impl.CommentServiceImpl;

import net.sf.json.JSONObject;

@WebServlet("/AddLikeOrUnLikeNumsAction")
public class AddLikeOrUnLikeNumsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CommentService cService=new CommentServiceImpl();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		int cid=Integer.parseInt(request.getParameter("comID"));
		int type=Integer.parseInt(request.getParameter("type"));
		if(1==type){
			boolean b=cService.addLikeNums(cid);
			int likeNum=cService.getLikeNums(cid);
			if(b==true){
				json.put("result", "success");
				json.put("likeNums", likeNum);
				out.write(json.toString());
				}else{
				json.put("result", "fail");
				out.write(json.toString());
			}
		}else{
			boolean b=cService.addUnLikeNums(cid);
			int unLikeNum=cService.getUnLikeNums(cid);
			if(b==true){
				json.put("result", "success");
				json.put("unLikeNums", unLikeNum);
				out.write(json.toString());
			}else{
				json.put("result", "fail");
				out.write(json.toString());
			}
		}
	}

}

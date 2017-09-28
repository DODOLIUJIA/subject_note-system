package com.zr.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Zxy
 *
 */
@WebServlet("/fileupload")
public class Fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String savePath = this.getServletContext().getRealPath("/statics/image/tempORC");
		File file = new File(savePath);

		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}

		SimpleDateFormat fileFormatter = new SimpleDateFormat("ddHHmmssSSS");
		String okPath = "";

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setHeaderEncoding("UTF-8");

			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}

			List<FileItem> list = upload.parseRequest(request);
			
			for (FileItem item : list) {

				if (!item.isFormField()) {
					String filename = item.getName();
					System.out.println(filename);
					
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					
					String filetype = filename.substring(filename.lastIndexOf(".") + 1);
					InputStream in = item.getInputStream();
					
					
					Date now = new Date();
					filename = fileFormatter.format(now) +"."+ filetype;
					

					okPath = savePath + "\\" + filename;

					FileOutputStream out = new FileOutputStream(okPath);
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					System.out.println("okPath: " + okPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write(okPath);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

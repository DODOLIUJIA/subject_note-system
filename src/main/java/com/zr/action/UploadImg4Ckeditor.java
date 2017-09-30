package com.zr.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value = "/uploadImg", initParams = { @WebInitParam(name = "baseDir", value = "/statics/"),
		@WebInitParam(name = "enabled", value = "true"), @WebInitParam(name = "debug", value = "false"),
		@WebInitParam(name = "AllowedExtensionsFile", value = ""),
		@WebInitParam(name = "DeniedExtensionsFile", value = "html|htm|php|php2|php3|php4|php5|phtml|pwml|inc|asp|aspx|ascx|jsp|cfm|cfc|pl|bat|exe|com|dll|vbs|js|reg|cgi|htaccess|asis|ftl"),
		@WebInitParam(name = "AllowedExtensionsImage", value = "jpg|gif|jpeg|png|bmp"),
		@WebInitParam(name = "DeniedExtensionsImage", value = ""),
		@WebInitParam(name = "AllowedExtensionsFlash", value = "swf|fla"),
		@WebInitParam(name = "DeniedExtensionsFlash", value = ""), })
/**
 * @author Zxy
 *
 */
public class UploadImg4Ckeditor extends HttpServlet {
	private static String baseDir;// CKEditor鐨勬牴鐩綍
	private static boolean debug = false;// 鏄惁debug妯″紡
	private static boolean enabled = false;// 鏄惁寮�惎CKEditor涓婁紶
	private static Hashtable allowedExtensions;// 鍏佽鐨勪笂浼犳枃浠舵墿灞曞悕
	private static Hashtable deniedExtensions;// 闃绘鐨勪笂浼犳枃浠舵墿灞曞悕
	private static SimpleDateFormat dirFormatter;// 鐩綍鍛藉悕鏍煎紡:yyyyMM
	private static SimpleDateFormat fileFormatter;// 鏂囦欢鍛藉悕鏍煎紡:yyyyMMddHHmmssSSS

	/**
	 * Servlet鍒濆鍖栨柟娉�
	 */
	public void init() throws ServletException {
		// 浠巜eb.xml涓鍙杁ebug妯″紡
		debug = (new Boolean(getInitParameter("debug"))).booleanValue();
		if (debug)
			System.out.println("\r\n---- SimpleUploaderServlet initialization started ----");
		// 鏍煎紡鍖栫洰褰曞拰鏂囦欢鍛藉悕鏂瑰紡
		dirFormatter = new SimpleDateFormat("yyyyMM");
		fileFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		// 浠巜eb.xml涓幏鍙栨牴鐩綍鍚嶇О
		baseDir = getInitParameter("baseDir");

		// 浠巜eb.xml涓幏鍙栨槸鍚﹀彲浠ヨ繘琛屾枃浠朵笂浼�
		enabled = (new Boolean(getInitParameter("enabled"))).booleanValue();

		if (baseDir == null)
			baseDir = "/statics/";

		String realBaseDir = getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);

		if (!baseFile.exists()) {
			baseFile.mkdirs();
		}
		// 瀹炰緥鍖栧厑璁哥殑鎵╁睍鍚嶅拰闃绘鐨勬墿灞曞悕
		allowedExtensions = new Hashtable(3);
		deniedExtensions = new Hashtable(3);

		// 浠巜eb.xml涓鍙栭厤缃俊鎭�
		allowedExtensions.put("file", stringToArrayList(getInitParameter("AllowedExtensionsFile")));
		deniedExtensions.put("file", stringToArrayList(getInitParameter("DeniedExtensionsFile")));

		allowedExtensions.put("image", stringToArrayList(getInitParameter("AllowedExtensionsImage")));
		deniedExtensions.put("image", stringToArrayList(getInitParameter("DeniedExtensionsImage")));

		allowedExtensions.put("flash", stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
		deniedExtensions.put("flash", stringToArrayList(getInitParameter("DeniedExtensionsFlash")));

		if (debug)
			System.out.println("---- SimpleUploaderServlet initialization completed ----\r\n");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (debug)
			System.out.println("--- BEGIN DOPOST ---");

		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		// 浠庤姹傚弬鏁颁腑鑾峰彇涓婁紶鏂囦欢鐨勭被鍨嬶細File/Image/Flash
		String fileType = request.getParameter("fileType");
		String workType = request.getParameter("workType");

		if (fileType == null) {
			fileType = "File";
		}

		if (workType == null) {
			workType = "default";
		}

		if (debug)
			System.out.println("typeStr: " + fileType);

		Date dNow = new Date();

		// 璁惧畾涓婁紶鏂囦欢璺緞 EX锛歴tatics/images/201709
		// String currentPath = baseDir + typeStr + "/" +
		// dirFormatter.format(dNow);

		// 璁惧畾涓婁紶鏂囦欢璺緞 EX锛歴tatics/images/note/201709
		String currentPath = baseDir + fileType + "/" + workType + "/" + dirFormatter.format(11);

		// 鑾峰緱web搴旂敤鐨勪笂浼犺矾寰�
		String currentDirPath = getServletContext().getRealPath(currentPath);
		// String currentDirPath =
		// request.getSession().getServletContext().getContextPath() +
		// currentPath;

		if (debug)
			System.out.println("currentDirPath: " + currentDirPath);

		// 鍒ゆ柇鏂囦欢澶规槸鍚﹀瓨鍦紝涓嶅瓨鍦ㄥ垯鍒涘缓
		File dirTest = new File(currentDirPath);

		if (!dirTest.exists()) {
			dirTest.mkdirs();
		}

		// 灏嗚矾寰勫墠鍔犱笂web搴旂敤鍚�
		currentPath = request.getContextPath() + currentPath;

		// 鏂囦欢鍚嶅拰鏂囦欢鐪熷疄璺緞
		String newName = "";
		String fileUrl = "";

		if (enabled) {
			// 浣跨敤Apache Common缁勪欢涓殑fileupload杩涜鏂囦欢涓婁紶
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List items = upload.parseRequest(request);
				Map fields = new HashMap();

				Iterator iter = items.iterator();

				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField())
						fields.put(item.getFieldName(), item.getString());
					else
						fields.put(item.getFieldName(), item);
				}
				// CEKditor涓璮ile鍩熺殑name鍊兼槸upload
				FileItem uplFile = (FileItem) fields.get("upload");

				// 鑾峰彇鏂囦欢鍚嶅苟鍋氬鐞�
				String fileNameLong = uplFile.getName();
				fileNameLong = fileNameLong.replace('\\', '/');
				String[] pathParts = fileNameLong.split("/");
				String fileName = pathParts[pathParts.length - 1];
				// 鑾峰彇鏂囦欢鎵╁睍鍚�
				String ext = getExtension(fileName);
				// 璁剧疆涓婁紶鏂囦欢鍚�
				fileName = fileFormatter.format(dNow) + "." + ext;
				// 鑾峰彇鏂囦欢鍚�鏃犳墿灞曞悕)
				String nameWithoutExt = getNameWithoutExtension(fileName);

				File pathToSave = new File(currentDirPath, fileName);
				fileUrl = currentPath + "/" + fileName;

				if (extIsAllowed(fileType, ext)) {
					int counter = 1;
					while (pathToSave.exists()) {
						newName = nameWithoutExt + "_" + counter + "." + ext;
						fileUrl = currentPath + "/" + newName;
						pathToSave = new File(currentDirPath, newName);
						counter++;
					}

					System.out.println("pathToSave: " + pathToSave);
					uplFile.write(pathToSave);
				} else {
					if (debug)
						System.out.println("鏃犳晥鐨勬枃浠剁被鍨嬶細 " + ext);
				}
			} catch (Exception ex) {
				if (debug)
					ex.printStackTrace();
			}
		} else {
			if (debug)
				System.out.println("鏈紑鍚疌KEditor涓婁紶鍔熻兘");
		}

		// CKEditorFuncNum鏄洖璋冩椂鏄剧ず鐨勪綅缃紝杩欎釜鍙傛暟蹇呴』鏈�
		String callback = request.getParameter("CKEditorFuncNum");
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl + "',''" + ")");
		out.println("</script>");
		out.flush();
		out.close();
		if (debug)
			System.out.println("--- END DOPOST ---");
	}

	/**
	 * 鑾峰彇鏂囦欢鍚嶇殑鏂规硶
	 */
	private static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 鑾峰彇鎵╁睍鍚嶇殑鏂规硶
	 */
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 瀛楃涓插儚ArrayList杞寲鐨勬柟娉�
	 */

	private ArrayList stringToArrayList(String str) {
		if (debug)
			System.out.println(str);
		String[] strArr = str.split("\\|");
		ArrayList tmp = new ArrayList();
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {
				if (debug)
					System.out.println(i + " - " + strArr[i]);
				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}

	/**
	 * 鍒ゆ柇鎵╁睍鍚嶆槸鍚﹀厑璁哥殑鏂规硶
	 */
	private boolean extIsAllowed(String fileType, String ext) {
		ext = ext.toLowerCase();
		ArrayList allowList = (ArrayList) allowedExtensions.get(fileType);
		ArrayList denyList = (ArrayList) deniedExtensions.get(fileType);
		if (allowList.size() == 0) {
			if (denyList.contains(ext)) {
				return false;
			} else {
				return true;
			}
		}
		if (denyList.size() == 0) {
			if (allowList.contains(ext)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}

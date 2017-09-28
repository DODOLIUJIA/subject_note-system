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
 * 姝ｅ父鐨勬枃浠朵笂浼狅紙鐜板彧鑳藉鐞嗗浘鐗囦笂浼狅級 
 * 寮傛鐨勬柟寮忥紝杩斿洖鍥剧墖瀛樺偍鐨勮矾寰�
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

		// 濡傛灉鏂囦欢澶圭洰褰曚笉瀛樺湪
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "鐩綍涓嶅瓨鍦紝闇�鍒涘缓");
			// 鍒涘缓鐩綍
			file.mkdir();
		}

		//鏍煎紡鍖栨枃浠跺悕鍛藉悕涓烘椂闂�
		SimpleDateFormat fileFormatter = new SimpleDateFormat("ddHHmmssSSS");
		// 娑堟伅鎻愮ず
		String okPath = "";

		try {
			// 浣跨敤Apache鏂囦欢涓婁紶缁勪欢澶勭悊鏂囦欢涓婁紶姝ラ锛�
			// 1銆佸垱寤轰竴涓狣iskFileItemFactory宸ュ巶
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 2銆佸垱寤轰竴涓枃浠朵笂浼犺В鏋愬櫒
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 瑙ｅ喅涓婁紶鏂囦欢鍚嶇殑涓枃涔辩爜
			upload.setHeaderEncoding("UTF-8");

			// 3銆佸垽鏂彁浜や笂鏉ョ殑鏁版嵁鏄惁鏄笂浼犺〃鍗曠殑鏁版嵁
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 鎸夌収浼犵粺鏂瑰紡鑾峰彇鏁版嵁
				//System.out.println("涓嶆槸琛ㄥ崟鑾峰彇鐨勬暟鎹�銆傘�銆�);
				return;
			}

			// 4銆佷娇鐢⊿ervletFileUpload瑙ｆ瀽鍣ㄨВ鏋愪笂浼犳暟鎹紝瑙ｆ瀽缁撴灉杩斿洖鐨勬槸涓�釜List<FileItem>闆嗗悎锛屾瘡涓�釜FileItem瀵瑰簲涓�釜Form琛ㄥ崟鐨勮緭鍏ラ」
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {

				// 濡傛灉fileitem涓皝瑁呯殑涓嶆槸鏅�杈撳叆椤圭殑鏁版嵁
				if (!item.isFormField()) {
					// fileitem涓皝瑁呯殑鏄笂浼犳枃浠�
					// 寰楀埌涓婁紶鐨勬枃浠跺悕绉�
					String filename = item.getName();
					System.out.println(filename);
					
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					
					// 娉ㄦ剰锛氫笉鍚岀殑娴忚鍣ㄦ彁浜ょ殑鏂囦欢鍚嶆槸涓嶄竴鏍风殑锛屾湁浜涙祻瑙堝櫒鎻愪氦涓婃潵鐨勬枃浠跺悕鏄甫鏈夎矾寰勭殑锛屽锛�
					// c:\a\b\1.txt锛岃�鏈変簺鍙槸鍗曠函鐨勬枃浠跺悕锛屽锛�.txt
					// 澶勭悊鑾峰彇鍒扮殑涓婁紶鏂囦欢鐨勬枃浠跺悕鐨勮矾寰勯儴鍒嗭紝鍙繚鐣欐枃浠跺悕閮ㄥ垎
					String filetype = filename.substring(filename.lastIndexOf(".") + 1);
					// 鑾峰彇item涓殑涓婁紶鏂囦欢鐨勮緭鍏ユ祦
					InputStream in = item.getInputStream();
					
					
					Date now = new Date();
					filename = fileFormatter.format(now) +"."+ filetype;
					

					okPath = savePath + "\\" + filename;

					// 鍒涘缓涓�釜鏂囦欢杈撳嚭娴�
					FileOutputStream out = new FileOutputStream(okPath);
					// 鍒涘缓涓�釜缂撳啿鍖�
					byte buffer[] = new byte[1024];
					// 鍒ゆ柇杈撳叆娴佷腑鐨勬暟鎹槸鍚﹀凡缁忚瀹岀殑鏍囪瘑
					int len = 0;
					// 寰幆灏嗚緭鍏ユ祦璇诲叆鍒扮紦鍐插尯褰撲腑锛�len=in.read(buffer))>0灏辫〃绀篿n閲岄潰杩樻湁鏁版嵁
					while ((len = in.read(buffer)) > 0) {
						// 浣跨敤FileOutputStream杈撳嚭娴佸皢缂撳啿鍖虹殑鏁版嵁鍐欏叆鍒版寚瀹氱殑鐩綍(savePath + "\\"
						// + filename)褰撲腑
						out.write(buffer, 0, len);
					}
					// 鍏抽棴杈撳叆娴�
					in.close();
					// 鍏抽棴杈撳嚭娴�
					out.close();
					// 鍒犻櫎澶勭悊鏂囦欢涓婁紶鏃剁敓鎴愮殑涓存椂鏂囦欢
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

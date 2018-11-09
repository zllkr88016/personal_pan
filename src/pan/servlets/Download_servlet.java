package pan.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pan.bean.UserFile;
import pan.dao.FileAndUserDao;

@WebServlet("/download")
public class Download_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse

	response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String range = request.getHeader("Range");
		String id = request.getParameter("id");
		String fileName = request.getParameter("fileName");
		FileAndUserDao fad = new FileAndUserDao();
		ArrayList<UserFile> fileList = fad.getFileList(id);
		for (UserFile uf : fileList) {
			if (uf.getFilename().equals(fileName)) {
				String path = uf.getFile_path();
				File file = new File(path + "\\" + uf.getFilename());
				if (file.exists()) {
					InputStream is = new FileInputStream(file);

					OutputStream os = response.getOutputStream();
					response.setContentLength((int) file.length());

					// 是否需要断点传输
					if (range == null) {
					} else {
						String[] ranges = range.substring(6).split("-");
						int startIndex = Integer.valueOf(ranges[0]);
						int endIndex = Integer.valueOf(ranges[1]);
						// 使输入流跳过startIndex个字节
						is.skip(startIndex);
						
						byte[] buffer = new byte[1024*1024];
						int len = -1;
						int currentSize=0;
						boolean f=false;
						while ((len = is.read(buffer)) != -1) {
							if(currentSize-1>endIndex){
								len=(int )(len-(currentSize-endIndex-1));
								f=true;
							}
							os.write(buffer, 0, len);
							currentSize+=len;
							if(f)
								break;
						}
						os.flush();
						os.close();
					}
				}

			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}
}

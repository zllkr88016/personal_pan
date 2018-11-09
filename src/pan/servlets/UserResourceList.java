package pan.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pan.bean.UserFile;
import pan.dao.FileAndUserDao;
@WebServlet("/userFileList")
public class UserResourceList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("id");
		System.out.println(id);
		ArrayList<UserFile> userFileList=new FileAndUserDao().getFileList(id);
		
		String file="";
		Iterator iterator=userFileList.iterator();
		int a=0;
		while(iterator.hasNext()){
			UserFile uf=(UserFile) iterator.next();
			
			if(a==userFileList.size()-1){
				file=file+uf.getFilename();
			}else{
				file=file+uf.getFilename()+"/";
			}
			a++;
		}
		System.out.println(file);
		if(file.equals("")){
			response.getOutputStream().write("null".getBytes());
		}else{
			response.getOutputStream().write(file.getBytes());
		}
	}
}

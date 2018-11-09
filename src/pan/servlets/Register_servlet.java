package pan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pan.dao.UserDao;
@WebServlet("/register")
public class Register_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UserDao ud=new UserDao();
		if(!ud.contains(username)){
			
			boolean a=ud.insertUser(username, password);
			if(a){
			response.getOutputStream().write("SUCCESS".getBytes());
			}else{
			System.out.println("发生未知错误，添加数据库信息失败");
			}
		}else{
			response.getOutputStream().write("FAIL".getBytes());
		}
		
	}

}

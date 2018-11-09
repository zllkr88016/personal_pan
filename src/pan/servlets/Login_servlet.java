package pan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pan.dao.UserDao;
@WebServlet("/login")
public class Login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("username:"+username+",password:"+password);
		UserDao ud=new UserDao();
		int userId=-1;
		if((userId=ud.isUser(username, password))!=-1){
			response.getOutputStream().write((userId+"").getBytes());
		}else{
			response.getOutputStream().write((userId+"").getBytes());
		}
	}
}

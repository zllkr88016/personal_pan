package pan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserDao {
	public  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pan",
					"root", "root");
			return conn;
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int isUser(String username,String password){
		int id=-1;
		Connection con=getConnection();
		try {
			PreparedStatement pstmt =con.prepareStatement("select * from user where username=?");
			pstmt.setString(1, username);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				if(username.equals(rs.getString("username"))&&password.equals(rs.getString("password"))){
					return id=rs.getInt("id");
				}
			}else{
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	public boolean contains(String username) {
		Connection con=getConnection();
		try {
			PreparedStatement pstmt =con.prepareStatement("select * from user where username=?");
			pstmt.setString(1, username);
			ResultSet rs=pstmt.executeQuery();
			if(!rs.next()){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public boolean insertUser(String username,String password){
		Connection con=getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("insert into user(username,password) values(?,?)");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	
}

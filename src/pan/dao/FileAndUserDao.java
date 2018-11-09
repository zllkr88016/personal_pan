package pan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pan.bean.UserFile;

public class FileAndUserDao {
	public  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pan_user_file",
					"root", "root");
			return conn;
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<UserFile> getFileList(String userId){
		ArrayList<UserFile> fileList=new ArrayList();
		Connection conn=getConnection();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from "+userId+"_file");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UserFile uf=new UserFile();
				uf.setId(rs.getInt("id"));
				uf.setFilename(rs.getString("filename"));
				uf.setFile_path(rs.getString("file_path"));
				uf.setFile_md5(rs.getString("file_md5"));
				fileList.add(uf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fileList;
	}
	public UserFile getFile(String userId,String fileId){
		UserFile userFileList=new UserFile();
		Connection conn=getConnection();
		try {
			PreparedStatement pstmt =conn.prepareStatement("select * from "+userId+"_file where id=?");
			pstmt.setInt(1, Integer.parseInt(userId));
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userFileList.setId(Integer.valueOf(fileId));
			userFileList.setFilename(rs.getString("filename"));
			userFileList.setFile_path(rs.getString("file_path"));
			userFileList.setFile_md5(rs.getString("file_md5"));
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userFileList;
	}
	
}

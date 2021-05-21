package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteService {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String number = request.getParameter("number");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&","root","1234");
			
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from cboard where number="+number);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String number = request.getParameter("number");
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
			String user ="root";
			String pwd = "1234";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			pstmt = conn.prepareStatement("update cboard set writer=?,subject=?,contents=? where number=?");
			
			pstmt.setString(1,writer);
			pstmt.setString(2,subject);
			pstmt.setString(3,contents);
			pstmt.setString(4,number);
			
			pstmt.executeUpdate();
			response.sendRedirect("list");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;

@WebServlet("/updateform")
public class updateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String number = request.getParameter("number");
		String writer="",subject="",contents="";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
			
			String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
			String user ="root";
			String pwd = "1234";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql="select * from cboard where number ="+number;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				BoardDTO board = new BoardDTO();
				int num = rs.getInt("number");
				writer = rs.getString("writer");
				subject = rs.getString("subject");
				contents = rs.getString("contents");
				
				board.setNumber(num);
				board.setWriter(writer);
				board.setSubject(subject);
				board.setContents(contents);
				
				request.setAttribute("board",board);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/update_form.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}

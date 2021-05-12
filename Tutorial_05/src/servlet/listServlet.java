package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;

@WebServlet("/list")
public class listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","1234");
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from cboard");
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				int number = rs.getInt("number");
				String writer = rs.getString("writer");
				String subject = rs.getString("subject");
				Date created = rs.getDate("created");
				
				board.setNumber(number);
				board.setSubject(subject);
				board.setWriter(writer);
				board.setCreated(created);
				list.add(board);
			}
			request.setAttribute("list",list);
			RequestDispatcher disp = request.getRequestDispatcher("/view/list.jsp");
			disp.forward(request, response);
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

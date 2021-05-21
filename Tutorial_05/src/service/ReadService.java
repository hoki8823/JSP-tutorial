package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;

public class ReadService {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String number = request.getParameter("number");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&","root","1234");
			pstmt = conn.prepareStatement("select * from cboard where number = ?");
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BoardDTO board = new BoardDTO();
				int num = rs.getInt("number");
				String subject = rs.getString("subject");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
				Date created = rs.getDate("created");
				
				board.setNumber(num);
				board.setWriter(writer);
				board.setSubject(subject);
				board.setCreated(created);
				board.setContents(contents);
				
				request.setAttribute("board",board);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close(); if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}

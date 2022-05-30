package com.board.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.DTO.MemberDTO;

public class MemberDAO {
    private static MemberDAO md = new MemberDAO();
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private int result;

    private MemberDAO() {
    }

    // 싱글톤 이용
    public static MemberDAO getInstance() {
	return md;
    }

    // oracle 연결
    public Connection getConnection() {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "lol";
	String password = "123";

	try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    conn = DriverManager.getConnection(url, id, password);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return conn;

    }

    // dto 클래스에 저장된 값을 테이블에 추가
    public int join(MemberDTO mt) {
	conn = this.getConnection();
	StringBuffer sb = new StringBuffer();
	sb.append("insert into member values(?, ?, ?, ?)");
	try {
	    pstmt = conn.prepareStatement(sb.toString());
	    pstmt.setString(1, mt.getId());
	    pstmt.setString(2, mt.getPassword());
	    pstmt.setString(3, mt.getName());
	    pstmt.setString(4, mt.getEmail());
	    result = pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    this.close(conn, pstmt, null);
	}

	return result;

    }

    // 로그인
    public int login(String id, String password) {
	conn = this.getConnection();
	StringBuffer sb = new StringBuffer();
	sb.append("SELECT password FROM member WHERE ID = ?");
	try {
	    pstmt = conn.prepareStatement(sb.toString());
	    pstmt.setString(1, id);
	    rs = pstmt.executeQuery();

	    if (rs.next()) {
		if (rs.getString("password").equals(password)) {
		    return 1;
		} else {
		    return 0;
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    this.close(conn, pstmt, rs);
	}
	return -1;
    }

    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	if (pstmt != null) {
	    try {
		pstmt.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }
}

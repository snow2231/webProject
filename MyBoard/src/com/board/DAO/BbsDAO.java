package com.board.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.DTO.BbsDTO;

public class BbsDAO {
    private static BbsDAO bd = new BbsDAO();
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private int result = 0;

    // 싱글톤 사용
    public static BbsDAO getInstance() {
	return bd;
    }

    // oracle 연결
    public Connection getConnect() {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "lol", password = "123";

	try {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    conn = DriverManager.getConnection(url, id, password);
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return conn;
    }

    // db에 저장되어 있는 BBSID 열의 최대값을 구해 리턴
    // BBSID 값이 현재 값보다 하나씩 증가하여 겹치지 않도록 하기 위함
    public int nextval() {
	conn = getConnect();
	StringBuffer sb = new StringBuffer();
	sb.append("SELECT MAX(bbsId)");
	sb.append("FROM bbs");

	try {
	    pstmt = conn.prepareStatement(sb.toString());
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		result = rs.getInt("MAX(bbsId)");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, rs);
	}
	return result;
    }

    // 게시글 작성 메소드
    public int write(BbsDTO bt) {
	conn = getConnect();
	StringBuffer sb = new StringBuffer();
	sb.append("INSERT INTO bbs ");
	sb.append("(bbsId, bbsTitle, bbsContent, bbsDate, bbsHit, id) ");
	sb.append("VALUES (?, ?, ?, sysdate, 0, ?)");
	try {
	    pstmt = conn.prepareStatement(sb.toString());
	    pstmt.setInt(1, bt.getBbsId());
	    pstmt.setString(2, bt.getBbsTitle());
	    pstmt.setString(3, bt.getBbsContent());
	    pstmt.setString(4, bt.getId());
	    result = pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, null);
	}
	return result;
    }

    // DB에 저장된 게시글 데이터를 가져오는 메소드
    public List<BbsDTO> selectList() {
	List<BbsDTO> list = new ArrayList<>();

	try {
	    conn = getConnect();
	    // select문으로 BBS 테이블의 내용을 불러옴
	    String sql = "SELECT * FROM bbs ORDER BY bbsId DESC";
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();

	    // BbsDTO의 set을 이용해 필드에 저장, list에 추가 반복
	    while (rs.next()) {
		BbsDTO bt = new BbsDTO();
		bt.setBbsId(rs.getInt("bbsId"));
		bt.setBbsTitle(rs.getString("bbsTitle"));
		bt.setBbsContent(rs.getString("bbsContent"));
		bt.setBbsDate(rs.getTimestamp("bbsDate"));
		bt.setBbsHit(rs.getInt("bbsHit"));
		bt.setId(rs.getString("id"));
		list.add(bt);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, rs);
	}
	return list;
    }

    // 게시글 열람시 해당 게시글 조회수 1 늘려주는 메소드
    public int hitUpdate(String bbsId) {
	conn = getConnect();
	String sql = "UPDATE bbs SET bbsHit = bbsHit + 1 WHERE bbsId = ?";

	try {
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, bbsId);
	    result = pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, null);
	}
	return result;
    }
    // bbsId를 입력 받아 해당 게시글의 데이터를 dto 형으로 리턴 
    public BbsDTO selectById(String bbsId) {
	BbsDTO bt = new BbsDTO();
	conn = getConnect();
	String sql = "SELECT * FROM bbs WHERE bbsId = ?";

	try {
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, bbsId);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
		bt.setBbsId(rs.getInt("bbsId"));
		bt.setBbsTitle(rs.getString("bbstitle"));
		bt.setBbsContent(rs.getString("bbscontent"));
		bt.setBbsDate(rs.getTimestamp("bbsdate"));
		bt.setBbsHit(rs.getInt("bbshit"));
		bt.setId(rs.getString("id"));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, rs);
	}
	return bt;
    }

    // 게시글 삭제
    public int del(int bbsId) {
	conn = getConnect();
	String sql = "DELETE FROM bbs WHERE bbsId = ?";

	try {
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, bbsId);
	    result = pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, null);
	}
	return result;
    }

    // 게시글 수정
    public int update(BbsDTO bt) {
	conn = getConnect();
	StringBuffer sb = new StringBuffer();
	sb.append("UPDATE bbs SET bbsTitle = ?, ");
	sb.append("bbsContent = ? ");
	sb.append("WHERE bbsId = ?");

	try {
	    pstmt = conn.prepareStatement(sb.toString());
	    pstmt.setString(1, bt.getBbsTitle());
	    pstmt.setString(2, bt.getBbsContent());
	    pstmt.setInt(3, bt.getBbsId());
	    result = pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    close(conn, pstmt, null);
	}
	return result;
    }

    // 종료
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

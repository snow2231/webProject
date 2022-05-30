package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.DAO.BbsDAO;
import com.board.DTO.BbsDTO;

public class WriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/write.jsp");
	rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	HttpSession session = req.getSession();
	// ID는 세션에 저장되어 있는 아이디 값을 가져오고, 없으면 "비회원"이란 값이 입력됨
	String sessionID = (String) session.getAttribute("sessionID");
	if (sessionID == null) {
	    sessionID = "비회원";
	}
	
	String bbsTitle = req.getParameter("bbsTitle");
	String bbsContent = req.getParameter("bbsContent");
	
	BbsDAO bd = BbsDAO.getInstance();
	BbsDTO bt = new BbsDTO();
	// BBS 테이블의 BBSID 열의 최대값에 1을 더함
	bt.setBbsId(bd.nextval() + 1);
	bt.setBbsTitle(bbsTitle);
	bt.setBbsContent(bbsContent);
	bt.setId(sessionID);

	int wResult = bd.write(bt);
	System.out.println(wResult);
	resp.sendRedirect("bbs.do");
    }
}

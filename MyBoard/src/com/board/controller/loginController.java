package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.DAO.MemberDAO;

public class loginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
	rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	String id = req.getParameter("id");
	String password = req.getParameter("password");

	MemberDAO md = MemberDAO.getInstance();
	int loginResult = md.login(id, password);

	// login 메소드에 입력된 아이디, 비밀번호가 db에 값과 같으면
	if (loginResult == 1) {
	    // "loginResult"라는 이름으로 리턴 값 저장
	    req.setAttribute("loginResult", loginResult);
	    HttpSession session = req.getSession();
	    // "sessionID"라는 이름으로 id 값 저장하여 index.jsp로 이동
	    session.setAttribute("sessionID", id);
	    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
	    rd.forward(req, resp);
	} else {  
	    req.setAttribute("loginResult", loginResult);
	    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
	    rd.forward(req, resp);
	}

    }
}

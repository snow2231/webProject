package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.DAO.MemberDAO;
import com.board.DTO.MemberDTO;

public class joinController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/signUp.jsp");
	rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        
        MemberDAO md = MemberDAO.getInstance();
        MemberDTO mt = new MemberDTO();
        mt.setId(id);
        mt.setPassword(password);
        mt.setName(name);
        mt.setEmail(email);
        int joinResult = md.join(mt);
        
        if(joinResult == 1) {
            req.setAttribute("joinResult", joinResult);
            HttpSession session = req.getSession();
            session.setAttribute("sessionId", id);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
            rd.forward(req, resp);
        }else {
            req.setAttribute("joinResult", 0);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/signUp.jsp");
            rd.forward(req, resp);
        }
    }
}

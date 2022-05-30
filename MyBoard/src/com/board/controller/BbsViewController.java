package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.DAO.BbsDAO;
import com.board.DTO.BbsDTO;

public class BbsViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	process(req, resp);
    }
    
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String bbsId = req.getParameter("bbsId");
	BbsDAO bd = BbsDAO.getInstance();
	BbsDTO bt = new BbsDTO();
	// hitupdate 메소드로 해당 게시글 조회수 1로 늘리고
	bd.hitUpdate(bbsId);
	// selectbyid 메소드로 해당 게시글 데이터 가져와 bbsdto에 넣고 
	bt = bd.selectById(bbsId);
	// bbsdto를 리퀘스트에 "bbsview"이름으로 저장하고 
	req.setAttribute("bbsview", bt);
	// bbsview.jsp 페이지로 넘김
	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsview.jsp");
	rd.forward(req, resp);
    }
}

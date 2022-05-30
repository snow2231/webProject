package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.DAO.BbsDAO;
import com.board.DTO.BbsDTO;

public class BbsUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String bbsId = req.getParameter("bbsId");
	BbsDAO bd = BbsDAO.getInstance();
	BbsDTO bt = new BbsDTO();
	bt = bd.selectById(bbsId);
	
	req.setAttribute("bbsupdate", bt);
	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsupdate.jsp");
	rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	
	String bbsId = req.getParameter("bbsId");
	String bbsTitle = req.getParameter("bbsTitle");
	String bbsContent = req.getParameter("bbsContent");
	
	BbsDAO bbsDao = BbsDAO.getInstance();
	BbsDTO bbsDto = new BbsDTO();
	bbsDto.setBbsId(Integer.parseInt(bbsId));
	bbsDto.setBbsTitle(bbsTitle);
	bbsDto.setBbsContent(bbsContent);
	
	bbsDao.update(bbsDto);
	resp.sendRedirect("bbs.do");
    }
}

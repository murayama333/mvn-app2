package com.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ItemDao;
import com.example.dto.Item;
import com.example.logic.ItemLogic;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");
		if("error".equals(type)){
			new ItemDao().setErrorData();
			resp.getWriter().println("CHANGE ERROR MODE.");
			return;
		}else if("bulk".equals(type)){
			new ItemDao().setBulkData(1000);
			resp.getWriter().println("CHANGE BULK MODE. : 1,000");
			return;
		}

		new ItemDao().setDefaultData();
		resp.getWriter().println("CHANGE DEFAULT MODE.");
	}

}

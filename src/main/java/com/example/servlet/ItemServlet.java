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

@WebServlet("/item")
public class ItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String keyword = req.getParameter("keyword");
		if (keyword != null && keyword.length() > 10) {
			keyword = keyword.substring(0, 10);
		}
		
		ItemLogic itemLogic = new ItemLogic();
		itemLogic.setItemDao(new ItemDao());		
		List<Item> items = itemLogic.getItems(keyword);
		
		req.setAttribute("items", items);
		req.getRequestDispatcher("/WEB-INF/jsp/item.jsp").forward(req, resp);
	}
}

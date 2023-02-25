package com.xadmin.usermanegment.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.usermanegment.bean.User;
import com.xadmin.usermanegment.dao.UserDao;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
	public void init() {
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();


			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				try {
					insertUser(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				try {
					updateUser(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				listUser(request, response);
				break;
			}
	
	}



private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String state = request.getParameter("state");

	User user = new User(id, name, email, state);
	userDao.updateUser(user);
	response.sendRedirect("list");		
	}

private void listUser(HttpServletRequest request, HttpServletResponse response) {
	try {
	List<User> listUser = userDao.selectAllUsers();
	request.setAttribute("listUser", listUser);
	RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
	dispatcher.forward(request, response);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
		User existingUser = userDao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
		userDao.deleteUser(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("list");		
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String state = request.getParameter("state");
		User newUser = new User(name, email, state);
		
		userDao.insertUser(newUser);
		response.sendRedirect("list");		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);		
	}

}

package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

@WebServlet("/GroupUsers")
public class GroupUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Connection conn = DbUtil.getConn();
			
			User[] users = User.loadAllByGroupId(conn, id);
			
			request.setAttribute("users", users);

			getServletContext().getRequestDispatcher("/WEB-INF/views/groupUsers.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

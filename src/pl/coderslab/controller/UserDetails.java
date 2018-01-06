package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		try {
			Connection conn = DbUtil.getConn();

			User user = User.loadUserById(conn, id);

			request.setAttribute("user", user);

			Solution[] solutions = Solution.loadAllByUserId(id, conn);

			request.setAttribute("solutions", solutions);

			getServletContext().getRequestDispatcher("/WEB-INF/views/userDetails.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

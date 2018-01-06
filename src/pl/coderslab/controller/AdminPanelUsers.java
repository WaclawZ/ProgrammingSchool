package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

@WebServlet("/AdminPanelUsers")
public class AdminPanelUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer editId = null, deleteId = null;

		try {

			editId = Integer.parseInt(request.getParameter("editId"));
			deleteId = Integer.parseInt(request.getParameter("delId"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (editId != null) {
			if (editId == 0) {

				Connection conn = null;
				Group[] groups = null;

				try {

					conn = DbUtil.getConn();
					groups = Group.loadAllGroups(conn);

				} catch (SQLException e) {
					e.printStackTrace();
				}

				request.setAttribute("groups", groups);

			} else {
				Connection conn;

				try {

					conn = DbUtil.getConn();

					Group[] groups = Group.loadAllGroups(conn);

					request.setAttribute("groups", groups);

					User user = User.loadUserById(conn, editId);

					request.setAttribute("user", user);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			getServletContext().getRequestDispatcher("/WEB-INF/views/editUsers.jsp").forward(request, response);

		} else if (deleteId != null) {

			try {

				Connection conn = DbUtil.getConn();

				User user = User.loadUserById(conn, deleteId);

				user.delete(conn);

				User[] users = User.loadAllUsers(conn);

				request.setAttribute("users", users);

				getServletContext().getRequestDispatcher("/WEB-INF/views/adminPanelUsers.jsp").forward(request,
						response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			try {

				Connection conn = DbUtil.getConn();

				User[] users = User.loadAllUsers(conn);

				request.setAttribute("users", users);

				getServletContext().getRequestDispatcher("/WEB-INF/views/adminPanelUsers.jsp").forward(request,
						response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = null, groupId = null;

		try {

			groupId = Integer.parseInt(request.getParameter("groupId"));
			id = Integer.parseInt(request.getParameter("id"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = null;
		if (id == null) {

			user = new User(username, email, password);

			user.setUser_group_id(groupId);

		} else {

			Connection conn = null;
			try {

				conn = DbUtil.getConn();

				user = User.loadUserById(conn, id);

				user.setUsername(username);
				user.setEmail(email);
				user.setUser_group_id(groupId);

				if (!password.equals("")) {
					user.setPassword(password);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Connection conn;
		try {
			conn = DbUtil.getConn();
			user.saveToDB(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
}

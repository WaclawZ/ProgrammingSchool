package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

@WebServlet("/SolutionDetails")
public class SolutionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = null, editId = null, deleteId = null;

		try {

			editId = Integer.parseInt(request.getParameter("editId"));
			id = Integer.parseInt(request.getParameter("id"));
			deleteId = Integer.parseInt(request.getParameter("delId"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (editId != null) {

			if (editId == 0) {

				Connection conn = null;
				Exercise[] ex = null;
				User[] users = null;

				try {

					conn = DbUtil.getConn();

					ex = Exercise.loadAllExercises(conn);

					users = User.loadAllUsers(conn);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("ex", ex);
				request.setAttribute("users", users);

				getServletContext().getRequestDispatcher("/WEB-INF/views/editSolutions.jsp").forward(request, response);
			} else {

				Connection conn;
				Exercise[] ex = null;
				User[] users = null;
				Solution sol = null;

				try {

					conn = DbUtil.getConn();

					ex = Exercise.loadAllExercises(conn);

					users = User.loadAllUsers(conn);

					sol = Solution.loadSolutionById(conn, editId);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("ex", ex);
				request.setAttribute("users", users);
				request.setAttribute("solution", sol);

				getServletContext().getRequestDispatcher("/WEB-INF/views/editSolutions.jsp").forward(request, response);
			}
		} else if (deleteId != null) {

			try {

				Connection conn = DbUtil.getConn();

				Solution sol = Solution.loadSolutionById(conn, deleteId);

				int userId = sol.getUser_id();

				sol.delete(conn);

				Solution[] sols = Solution.loadAllSolutions(conn);

				request.setAttribute("solutions", sols);

				getServletContext().getRequestDispatcher("/WEB-INF/views/UserDetails?id=" + userId + ".jsp")
						.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (id != null) {

			try {
				Connection conn = DbUtil.getConn();

				Solution sol = Solution.loadSolutionById(conn, id);

				request.setAttribute("solution", sol);

				getServletContext().getRequestDispatcher("/WEB-INF/views/solutionDetails.jsp").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			getServletContext().getRequestDispatcher("/WEB-INF/views/groups.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = null, userId = null, exId = null;

		try {

			exId = Integer.parseInt(request.getParameter("exId"));
			userId = Integer.parseInt(request.getParameter("userId"));
			id = Integer.parseInt(request.getParameter("id"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String desc = request.getParameter("sol");
		Solution sol = null;

		if (id == null) {

			sol = new Solution();

			sol.setExercise_id(exId);

			sol.setUser_id(userId);

			sol.setDescription(desc);

		} else {

			Connection conn = null;
			try {

				conn = DbUtil.getConn();

				sol = Solution.loadSolutionById(conn, id);

				sol.setExercise_id(exId);

				sol.setUser_id(userId);

				sol.setDescription(desc);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Connection conn;

		try {

			conn = DbUtil.getConn();

			sol.saveToDB(conn);

			doGet(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

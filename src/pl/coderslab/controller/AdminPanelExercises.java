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
import pl.coderslab.utils.DbUtil;

@WebServlet("/AdminPanelExercises")
public class AdminPanelExercises extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer editId = null, showId = null, deleteId = null;

		try {

			editId = Integer.parseInt(request.getParameter("editId"));
			deleteId = Integer.parseInt(request.getParameter("deleteId"));
			showId = Integer.parseInt(request.getParameter("showId"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (editId != null) {
			Connection conn;

			try {

				conn = DbUtil.getConn();
				Exercise ex = Exercise.loadExerciseById(conn, editId);
				request.setAttribute("ex", ex);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/WEB-INF/views/editExercise.jsp").forward(request, response);

		} else if (showId != null) {

			try {

				Connection conn = DbUtil.getConn();

				Solution[] sol = Solution.loadAllByExerciseId(showId, conn);

				Exercise ex = Exercise.loadExerciseById(conn, showId);

				request.setAttribute("ex", ex);

				request.setAttribute("sol", sol);

				getServletContext().getRequestDispatcher("/WEB-INF/views/exerciseDetails.jsp").forward(request,
						response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (deleteId != null) {

			try {

				Connection conn = DbUtil.getConn();

				Exercise ex = Exercise.loadExerciseById(conn, deleteId);

				Solution[] solutions = Solution.loadAllByExerciseId(deleteId, conn);

				for (Solution sol : solutions) {

					sol.delete(conn);

				}

				ex.delete(conn);

				Exercise[] eX = Exercise.loadAllExercises(conn);

				request.setAttribute("ex", eX);

				getServletContext().getRequestDispatcher("/WEB-INF/views/adminPanelExercises.jsp").forward(request,
						response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			try {

				Connection conn = DbUtil.getConn();

				Exercise[] ex = Exercise.loadAllExercises(conn);

				request.setAttribute("ex", ex);

				getServletContext().getRequestDispatcher("/WEB-INF/views/adminPanelExercises.jsp").forward(request,
						response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = null;

		try {

			id = Integer.parseInt(request.getParameter("id"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String title = request.getParameter("title");
		String desc = request.getParameter("description");
		Exercise ex = null;

		if (id == null) {

			id = 0;
			ex = new Exercise(title, desc);

		} else {

			Connection conn = null;

			try {

				conn = DbUtil.getConn();

				ex = Exercise.loadExerciseById(conn, id);

				ex.setTitle(title);

				ex.setDescription(desc);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		Connection conn;

		try {

			conn = DbUtil.getConn();

			ex.saveToDB(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

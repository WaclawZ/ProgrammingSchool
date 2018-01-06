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
import pl.coderslab.utils.DbUtil;

@WebServlet("/AdminPanelGroups")
public class AdminPanelGroups extends HttpServlet {
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

			Connection conn;
			try {

				conn = DbUtil.getConn();

				Group group = Group.loadGroupById(conn, editId);

				request.setAttribute("group", group);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			getServletContext().getRequestDispatcher("/WEB-INF/views/editGroups.jsp").forward(request, response);

		} else if (deleteId != null) {

			try {

				Connection conn = DbUtil.getConn();

				Group group = Group.loadGroupById(conn, deleteId);

				group.delete(conn);

				Group[] groups = Group.loadAllGroups(conn);

				request.setAttribute("groups", groups);

				getServletContext().getRequestDispatcher("/WEB-INF/views/adminPanelGroups.jsp").forward(request,
						response);			

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			try {

				Connection conn = DbUtil.getConn();

				Group[] groups = Group.loadAllGroups(conn);

				request.setAttribute("groups", groups);

				getServletContext().getRequestDispatcher("/WEB-INF/views/adminPanelGroups.jsp").forward(request,
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
		
		String name = request.getParameter("name");

		Group group = null;
		if (id == null) {
			
			id = 0;
			group = new Group(name);
			
		}
		else {
			
			Connection conn = null;
			try {
				
				conn = DbUtil.getConn();
				
				group = Group.loadGroupById(conn, id);
				
				group.setName(name);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Connection conn;
		try {
			
			conn = DbUtil.getConn();
			
			group.saveToDB(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

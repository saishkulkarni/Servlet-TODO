package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;

@WebServlet("/addtask")
public class AddTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		int days = Integer.parseInt(req.getParameter("days"));

		Task task = new Task();
		task.setName(name);
		task.setDescription(description);
		task.setTaskDate(LocalDate.now());
		task.setCompletetionDate(LocalDate.now().plusDays(days));

		UserDao dao = new UserDao();
		dao.save(task);

		resp.getWriter().print("<h1 style='color:green'>Task Added Success</h1>");
		req.setAttribute("list", dao.fetchAllTasks());
		req.getRequestDispatcher("Home.jsp").include(req, resp);
	}
}

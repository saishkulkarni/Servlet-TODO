package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.User;

@WebServlet("/updatetask")
public class UpdateTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {

			int id=Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String description = req.getParameter("description");
			int days = Integer.parseInt(req.getParameter("days"));

			Task task = new Task();
			task.setId(id);
			task.setName(name);
			task.setDescription(description);
			task.setTaskDate(LocalDate.now());
			task.setCompletetionDate(LocalDate.now().plusDays(days));
			
			UserDao dao=new UserDao();
			dao.update(task);
			
			User user2 = dao.fetchByEmail(user.getEmail());

			req.getSession().setAttribute("user", user2);

			resp.getWriter().print("<h1 style='color:green'>Task Updated Success</h1>");
			req.setAttribute("list", user2.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
	}
}
}

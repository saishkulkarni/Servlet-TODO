package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.User;

@WebServlet("/changestatus")
public class ChangeStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {

			int id = Integer.parseInt(req.getParameter("id"));

			UserDao dao = new UserDao();
			Task task = dao.fetchTask(id);
			
			if(task.isStatus())
				task.setStatus(false);
			else
			task.setStatus(true);
			
			dao.update(task);
			
			User user2=dao.fetchByEmail(user.getEmail());
			
			
			req.getSession().setAttribute("user",user2 );
			

			resp.getWriter().print("<h1 style='color:green'>Task Updated Success</h1>");
			req.setAttribute("list", user2.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	}
}

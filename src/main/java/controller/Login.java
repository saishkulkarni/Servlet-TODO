package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDao dao = new UserDao();
		User user = dao.fetchByEmail(email);
		if (user == null) {
			resp.getWriter().print("<h1 style='color:red'>Invalid Email</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			if (user.getPassword().equals(password)) {
				HttpSession httpSession=req.getSession();
				httpSession.setAttribute("user", user);
				httpSession.setMaxInactiveInterval(30);
				resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
				req.setAttribute("list", user.getTasks());
				req.getRequestDispatcher("Home.jsp").include(req, resp);
			} else {
				resp.getWriter().print("<h1 style='color:red'>Invalid Password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}

		}
	}
}

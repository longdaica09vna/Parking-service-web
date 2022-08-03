package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fa.training.dao.EmployeeDAO;
import fa.training.daoImpl.EmployeeDAOImpl;
import fa.training.model.Employee;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("resources/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Employee employee = new Employee();
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		try {
			employee = employeeDAO.login(account, password);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("resources/index.jsp").forward(request,response);
		} finally {
			if (employee != null) {
				HttpSession session = request.getSession();
				session.setAttribute("LoggedIn", employee);
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}else {
				request.setAttribute("errorLogin", true);
				request.getRequestDispatcher("resources/views/login.jsp").forward(request, response);
			}
		}
	}

}

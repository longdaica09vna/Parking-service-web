package fa.training.controller;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class AddEmployeeController
 */
@WebServlet("/AddEmployeeController")
public class AddEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployeeController() {
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
		EmployeeDAO dao = new EmployeeDAOImpl();
		long id = -1;
		if (request.getParameter("id") != null) {
			id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("edit", true);
		}else {
			request.setAttribute("add", true);
		}
		try {
			Employee employee = dao.getById(id);
			request.setAttribute("employee", employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		} finally {
			request.setAttribute("EmployeePage", true);
			request.getRequestDispatcher("resources/views/addEmployee.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = request.getParameter("id")==""?-1:Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		Date dob = Date.valueOf(request.getParameter("dob"));
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String department = request.getParameter("department");
		Employee employee = new Employee(id, account, department, address, dob, email, name, phone,
				password, sex);
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		boolean check = false;
		try {
			if (request.getParameter("button").equals("Edit")) {
				check = employeeDAO.EditEmployee(employee);
				HttpSession session = request.getSession();
				Employee LoggedIn = (Employee)session.getAttribute("LoggedIn");
				if(LoggedIn.getEmployeeId()==id) {
					session.setAttribute("LoggedIn", employee);
				}
				request.setAttribute("edit", true);
			} else {
				check = employeeDAO.AddEmployee(employee);
				request.setAttribute("add", true);
			}
			if (check) {
				response.sendRedirect(request.getContextPath() + "/ViewListEmployee");
			} else {
				if (request.getParameter("button").equals("Edit")) {
					request.setAttribute("editFail", true);
					request.setAttribute("edit", true);
					request.setAttribute("EmployeePage", true);
				} else {
					request.setAttribute("addFail", true);
					request.setAttribute("add", true);
					request.setAttribute("EmployeePage", true);
				}
				request.getRequestDispatcher("resources/views/addTrip.jsp").forward(request,
						response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
	}

}

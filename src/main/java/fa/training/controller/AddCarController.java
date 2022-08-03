package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.CarDAO;
import fa.training.daoImpl.CarDAOImpl;
import fa.training.daoImpl.ParkinglotDAOImpl;
import fa.training.model.Car;

/**
 * Servlet implementation class AddCarControll
 */
@WebServlet("/AddCarController")
public class AddCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCarController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ParkinglotDAOImpl parkinglotDAO = new ParkinglotDAOImpl();
		String id = null;
		CarDAO carDAO = new CarDAOImpl();
		try {
			if (request.getParameter("id") != null) {
				id = request.getParameter("id");
				request.setAttribute("edit", true);
				request.setAttribute("car", carDAO.getByPlate(id));
			} else {
				request.setAttribute("add", true);
			}
			request.setAttribute("listParkinglot", parkinglotDAO.getAllParking());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}finally {
			request.setAttribute("CarPage", true);
			request.getRequestDispatcher("resources/views/addCar.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String licensePlate = request.getParameter("license");
		String type = request.getParameter("type");
		String color = request.getParameter("color");
		String company = request.getParameter("company");
		long park_ID = Long.parseLong(request.getParameter("parkingLot"));
		Car car = new Car(licensePlate, color, type, company, park_ID);
		CarDAO cd = new CarDAOImpl();
		ParkinglotDAOImpl parkinglotDAO = new ParkinglotDAOImpl();
		boolean check = false;
		try {

			if (request.getParameter("button").equals("Edit")) {
				check = cd.editCar(car);
				request.setAttribute("edit", true);
			} else {
				check = cd.addCar(car);
				request.setAttribute("add", true);
			}
			if (check) {
				request.setAttribute("CarPage", true);
				response.sendRedirect(request.getContextPath() + "/DisplayListCarController");
			} else {
				if (request.getParameter("button").equals("Edit")) {
					request.setAttribute("editFail", true);
					request.setAttribute("edit", true);
					request.setAttribute("CarPage", true);
				} else {
					request.setAttribute("addFail", true);
					request.setAttribute("add", true);
					request.setAttribute("CarPage", true);
				}
				request.setAttribute("listParkinglot", parkinglotDAO.getAllParking());
				request.setAttribute("car", car);
				request.getRequestDispatcher("resources/views/addCar.jsp").forward(request,
						response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}

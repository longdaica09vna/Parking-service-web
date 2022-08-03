package fa.training.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.CarDAO;
import fa.training.dao.TicketDAO;
import fa.training.daoImpl.CarDAOImpl;
import fa.training.daoImpl.TicketDAOImpl;

/**
 * Servlet implementation class DeleteCarController
 */
@WebServlet("/DeleteCarController")
public class DeleteCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCarController() {
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
		String id = request.getParameter("id");
		TicketDAO ticketDAO = new TicketDAOImpl();
		CarDAO carDAO = new CarDAOImpl();
		try {
			ticketDAO.deleteTicketByCar(id);
			if (carDAO.DeleteCar(id)) {
				response.sendRedirect(request.getContextPath() + "/DisplayListCarController");
			}else {
				request.setAttribute("errorDelete", true);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

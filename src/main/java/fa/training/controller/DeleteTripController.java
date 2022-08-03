package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.TicketDAO;
import fa.training.dao.TripDAO;
import fa.training.daoImpl.BookingOfficeDAOImpl;
import fa.training.daoImpl.TicketDAOImpl;
import fa.training.daoImpl.TripDAOImpl;

/**
 * Servlet implementation class DeleteTripController
 */
@WebServlet("/DeleteTripController")
public class DeleteTripController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTripController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("id"));
		TripDAO dao = new TripDAOImpl();
		TicketDAO ticketDAO = new TicketDAOImpl();
		BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAOImpl();
		try {
			ticketDAO.deleteTicketByTrip(id);
			bookingOfficeDAO.deleteBookingOfficeByTrip(id);
			if(dao.deleteTrip(id)) {
				response.sendRedirect("TripListController");
			}else {
				request.setAttribute("errorDelete", true);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

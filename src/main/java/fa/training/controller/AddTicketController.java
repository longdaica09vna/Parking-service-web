package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.CarDAO;
import fa.training.dao.TicketDAO;
import fa.training.dao.TripDAO;
import fa.training.daoImpl.BookingOfficeDAOImpl;
import fa.training.daoImpl.CarDAOImpl;
import fa.training.daoImpl.TicketDAOImpl;
import fa.training.daoImpl.TripDAOImpl;
import fa.training.model.Ticket;

/**
 * Servlet implementation class AddTicketController
 */
@WebServlet("/addTicketController")
public class AddTicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTicketController() {
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
		TicketDAO ticketDAO = new TicketDAOImpl();
		TripDAO tripDAO = new TripDAOImpl();
		CarDAO carDAO = new CarDAOImpl();
		BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAOImpl();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (request.getParameter("id") != null) {
				long id = Long.parseLong(request.getParameter("id"));
				request.setAttribute("view", true);
				request.setAttribute("bookingOfficeDAO", bookingOfficeDAO);
				request.setAttribute("ticket", ticketDAO.getById(id));
				request.setAttribute("df", df);
			}
			request.setAttribute("trips", tripDAO.getAll());
			request.setAttribute("cars", carDAO.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} finally {
			request.setAttribute("TicketPage", true);
			request.getRequestDispatcher("resources/views/addTicket.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		TicketDAO ticketDAO = new TicketDAOImpl();
		TripDAO tripDAO = new TripDAOImpl();
		CarDAO carDAO = new CarDAOImpl();
		try {
			String customerName = request.getParameter("customerName");
			SimpleDateFormat df = new SimpleDateFormat("hh:mm");
			Long time = df.parse(request.getParameter("booktime")).getTime();
			Time bookingTime = new Time(time);
			String licensePlate = request.getParameter("licensePlates");
			long trip_ID = Long.parseLong(request.getParameter("trip"));
			long ticket_ID = request.getParameter("id")==""?-1:Long.parseLong(request.getParameter("id"));
			Ticket ticket = new Ticket(ticket_ID, bookingTime, customerName, licensePlate, trip_ID);
			boolean check = false;
			if (request.getParameter("button").equals("Edit")) {
				check = ticketDAO.editTicket(ticket);
			} else {
				check = ticketDAO.addTicket(ticket);
				tripDAO.updateBookedTicket(trip_ID);
			}
			if (check) {
				response.sendRedirect(request.getContextPath() + "/ticketListController");
			} else {
				if (request.getParameter("button").equals("Edit")) {
					request.setAttribute("edit", true);
					request.setAttribute("errorEdit", true);
				} else {
					request.setAttribute("add", true);
					request.setAttribute("errorAdd", true);
				}
				request.setAttribute("TicketPage", true);
				request.setAttribute("trips", tripDAO.getAll());
				request.setAttribute("cars", carDAO.getAll());
				request.setAttribute("ticket", ticket);
				request.getRequestDispatcher("resources/views/addTicket.jsp").forward(request,
						response);
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}

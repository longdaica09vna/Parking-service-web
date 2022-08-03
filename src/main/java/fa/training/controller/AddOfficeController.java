package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.BookingOfficeDAO;
import fa.training.dao.TripDAO;
import fa.training.daoImpl.BookingOfficeDAOImpl;
import fa.training.daoImpl.TripDAOImpl;
import fa.training.model.BookingOffice;

/**
 * Servlet implementation class AddOfficeController
 */
@WebServlet("/addOfficeController")
public class AddOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOfficeController() {
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
		BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAOImpl();
		TripDAO tripDAO = new TripDAOImpl();
		String temp = request.getParameter("id");
		
		try {
			if(temp!=null) {
				long id = Long.parseLong(temp);
				request.setAttribute("bookingOffice", bookingOfficeDAO.getByID(id));
				request.setAttribute("bookingOffices", bookingOfficeDAO.getAll());
				request.setAttribute("edit", true);
				request.setAttribute("BookingOfficePage", true);
			}else {
				request.setAttribute("add", true);
			}
			request.setAttribute("trips", tripDAO.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		} finally {
			request.setAttribute("BookingOfficePage", true);
			request.getRequestDispatcher("resources/views/addOffice.jsp").forward(request,
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
		BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAOImpl();
		TripDAO tripDAO = new TripDAOImpl();
		BookingOffice bookingOffice = null;
		try {
			String temp = request.getParameter("id");
			long id = -1;
			if(temp!=null) {
				id = Long.parseLong(temp);
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			String name = request.getParameter("name");
			long trip = Long.parseLong(request.getParameter("trip"));
			String phone = request.getParameter("phone");
			String place = request.getParameter("place");
			double price = Double.parseDouble(request.getParameter("price"));
			Date from = df.parse(request.getParameter("dateFrom"));
			Date to = df.parse(request.getParameter("dateTo"));
			bookingOffice = new BookingOffice(id, name, phone, place, price, from, to, trip);
			boolean check = false;
			if(request.getParameter("button").equals("Edit")) {
				check = bookingOfficeDAO.editBookingOffice(bookingOffice);
			}else {
				check = bookingOfficeDAO.addBookingOffice(bookingOffice);
			}
			if (check) {
				response.sendRedirect(request.getContextPath() + "/bookingOfficeController");
			} else {
				if(request.getParameter("button").equals("Edit")) {
					request.setAttribute("edit", true);
					request.setAttribute("errorEdit", true);
				}else {
					request.setAttribute("add", true);
					request.setAttribute("errorAdd", true);
				}
				request.setAttribute("bookingOffices", bookingOfficeDAO.getAll());
				request.setAttribute("trips", tripDAO.getAll());
				request.setAttribute("bookingOffice", bookingOffice);
				request.getRequestDispatcher("resources/views/addOffice.jsp").forward(request,
						response);
			}
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}

	}

}

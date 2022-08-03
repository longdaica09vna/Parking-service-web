package fa.training.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.TripDAO;
import fa.training.daoImpl.TripDAOImpl;
import fa.training.model.Trip;
import fa.training.utils.DateUtils;

/**
 * Servlet implementation class AddTripController
 */
@WebServlet("/AddTripController")
public class AddTripController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTripController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TripDAO tripDAO = new TripDAOImpl();
		String temp = request.getParameter("id")==null?null:request.getParameter("id");
		if(temp!=null) {
			long id = Long.parseLong(temp);
			try {
				request.setAttribute("trip", tripDAO.getByID(id));
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorSQL", true);
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
			request.setAttribute("edit", true);
		}else {
			request.setAttribute("add", true);
		}
		request.setAttribute("TripPage", true);
		request.getRequestDispatcher("resources/views/addTrip.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = request.getParameter("id")==null?-1:Long.parseLong(request.getParameter("id"));
		int bookedTicketNumber = request.getParameter("bookedTicketNumber")==""?0:Integer.parseInt(request.getParameter("bookedTicketNumber"));
		String carType = request.getParameter("carType");
		Date departureDate = DateUtils.convertStringToDate(request.getParameter("departureDate"));
		Time departureTime = Time.valueOf(request.getParameter("departureTime") + ":00");
		String destination = request.getParameter("destination");
		String driver = request.getParameter("driver");
		int maximumOnlineTicketNumber = Integer.parseInt(request.getParameter("maximumOnlineTicketNumber"));
		
		Trip newTrip = new Trip(id,bookedTicketNumber,carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber);
		boolean check = false;
		TripDAO dao = new TripDAOImpl();
		try {
			if(request.getParameter("button").equals("Edit")) {
				check = dao.editTrip(newTrip);
			}else {
				check = dao.addTrip(newTrip);
			}
			if(check) {
				response.sendRedirect(request.getContextPath() + "/TripListController");
			}else {
				if(request.getParameter("button").equals("Edit")) {
					request.setAttribute("editFail", true);
					request.setAttribute("TripPage", true);
				}else {
					request.setAttribute("addFail", true);
					request.setAttribute("TripPage", true);
				}
				request.getRequestDispatcher("resources/views/addTrip.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		
	}

}

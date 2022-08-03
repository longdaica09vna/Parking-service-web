package fa.training.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.TicketDAO;
import fa.training.dao.TripDAO;
import fa.training.daoImpl.TicketDAOImpl;
import fa.training.daoImpl.TripDAOImpl;
import fa.training.model.Ticket;

/**
 * Servlet implementation class TicketListController
 */
@WebServlet("/ticketListController")
public class ViewTicketListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewTicketListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TicketDAO ticketDAO = new TicketDAOImpl();
		TripDAO tripDAO = new TripDAOImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		try {
			int year = Integer.parseInt(
					request.getParameter("year") == null ? "0" : request.getParameter("year"));
			int month = Integer.parseInt(
					request.getParameter("month") == null ? "-1" : request.getParameter("month"));
			int day = Integer.parseInt(
					request.getParameter("day") == null ? "0" : request.getParameter("day"));
			String search = "";
			search = request.getParameter("search");
			if (search == null)
				search = "";
			int page = 1;
			int records = 5;
			String filter = request.getParameter("filter");
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));

			int noOfRecords = ticketDAO.getTotalRow();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
			if(noOfRecords==0) {
				noOfPages = 1;
			}
			List<Ticket> tickets = null;
			if (year == 0 && month == -1 && day == 0 && search.equals("")) {
				tickets = ticketDAO.getByPage((page - 1) * records, records);
			} else if (year == 0 && month == -1 && day == 0 && !search.equals("")) {
				tickets = ticketDAO.getByPage((page - 1) * records, records, filter, search);
				noOfRecords = ticketDAO.getTotalRowSearch(filter, search);
				noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
				request.setAttribute("isSearching", true);
			} else if (year != 0 && month != -1 && day != 0 && search.equals("")) {
				Date date = new Date(df.parse(year + "-" + (month + 1) + "-" + day).getTime());
				tickets = ticketDAO.getByPage((page - 1) * records, records, date);
				noOfRecords = ticketDAO.getTotalRowSearch(date);
				noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
				request.setAttribute("isSearchingDate", true);
			} else if (year != 0 && month != -1 && day != 0 && !search.equals("")) {
				Date date = new Date(df.parse(year + "-" + (month + 1) + "-" + day).getTime());
				tickets = ticketDAO.getByPage((page - 1) * records, records, filter, search, date);
				noOfRecords = ticketDAO.getTotalRowSearch(filter, search, date);
				noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
				if(noOfRecords==0) {
					noOfPages = 1;
				}
				request.setAttribute("isSearchingDate", true);
				request.setAttribute("isSearching", true);
			}
			request.setAttribute("tickets", tickets);
			request.setAttribute("filter", filter);
			request.setAttribute("search", search);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("day", day);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} finally {
			request.setAttribute("tripDAO", tripDAO);
			request.setAttribute("TicketPage", true);
			request.getRequestDispatcher("resources/views/listTicket.jsp").forward(request,
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
		doGet(request, response);
	}

}

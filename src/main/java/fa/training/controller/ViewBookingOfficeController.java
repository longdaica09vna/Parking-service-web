package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class BookingListController
 */
@WebServlet("/bookingOfficeController")
public class ViewBookingOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewBookingOfficeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookingOfficeDAO bookingOfficeDAO = new BookingOfficeDAOImpl();
		TripDAO tripDAO = new TripDAOImpl();
		int page = 1;
        int records = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
		try {
			int noOfRecords = bookingOfficeDAO.getTotalRow();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
			if(noOfRecords==0) {
				noOfPages = 1;
			}
			String search = "";
			search = request.getParameter("search");
			if(search==null) search = "";
			if(!search.isEmpty()) {
				String filter = request.getParameter("filter");
				request.setAttribute("filter", filter);
				request.setAttribute("search", search);
				List<BookingOffice> bookingOffices = bookingOfficeDAO.getByPage((page-1)*records, records, filter, search);
				noOfRecords = bookingOfficeDAO.getTotalRowSearch(filter, search);
				System.out.println(noOfRecords);
				noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
				if(noOfRecords==0) {
					noOfPages = 1;
				}
				System.out.println(noOfPages);
				request.setAttribute("isSearching", true);
				request.setAttribute("bookingOffices", bookingOffices);
			}else{
				request.setAttribute("bookingOffices", bookingOfficeDAO.getByPage((page-1)*records, records));
			}
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
			request.setAttribute("BookingOfficePage", true);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		} finally {
			request.setAttribute("tripDAO", tripDAO);
			request.getRequestDispatcher("resources/views/listBookingOffice.jsp").forward(request,response);
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

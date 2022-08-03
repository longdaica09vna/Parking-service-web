package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.TripDAO;
import fa.training.daoImpl.TripDAOImpl;
import fa.training.model.Trip;

/**
 * Servlet implementation class TripListController
 */
@WebServlet("/TripListController")
public class ViewTripListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewTripListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int records = 5;
		try {
			String search = request.getParameter("gsearch");
			search = search == null ? "" : search;
			String s_day = request.getParameter("day");
			int day = s_day == null ? 0 : Integer.parseInt(s_day);
			String s_month = request.getParameter("month");
			int month = s_month == null ? 0 : Integer.parseInt(s_month);
			String s_year = request.getParameter("year");
			int year = s_year == null ? 0 : Integer.parseInt(s_year);
			int page = 1;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));

			TripDAO dao = new TripDAOImpl();

			List<Trip> lst = dao.getAll();
			List<Trip> searchList = dao.getAllBySearch(lst, search, day, month, year);
			int noOfPages = (int) Math.ceil(searchList.size() * 1.0 / records);
			if(searchList.size()==0) {
				noOfPages = 1;
			}
			List<Trip> pageList = dao.getByPage(searchList, (page - 1) * records, records);
			request.setAttribute("pageList", pageList);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);

			request.setAttribute("gsearch", search);
			request.setAttribute("sday", day);
			request.setAttribute("smonth", month);
			request.setAttribute("syear", year);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}finally {
			request.setAttribute("TripPage", true);
			request.getRequestDispatcher("resources/views/listTrip.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}

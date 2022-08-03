package fa.training.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.ParkinglotDAO;
import fa.training.daoImpl.ParkinglotDAOImpl;
import fa.training.model.Parkinglot;

/**
 * Servlet implementation class ViewParkingServlet
 */
@WebServlet("/ViewParkingServlet")
public class ViewParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewParkingServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String keyword = request.getParameter("keyword")==null?null:request.getParameter("keyword");
			String type = request.getParameter("type")==null?null:request.getParameter("type");
			List<Parkinglot> parkings = new ArrayList<Parkinglot>();
			int page = 1, pageSize = 1;
			int totalRow = 0;
			if (request.getParameter("page") != null) { // check param page
				page = Integer.parseInt(request.getParameter("page")); // get param page
			}
			ParkinglotDAO dao = new ParkinglotDAOImpl();
			if (keyword == null) {
				totalRow = dao.getTotalRow();
				parkings = dao.getParkings(page, pageSize);
			} else {
				totalRow = dao.getTotalRowSearch(type, keyword);
				
				parkings = dao.searchParkings(page, pageSize, type, keyword);
				
				request.setAttribute("isSearching", true);
			}
			int noOfPages = (int) Math.ceil(totalRow * 1.0 / pageSize);
			if(noOfPages==0) {
				noOfPages = 1;
			}
			if (page > noOfPages && page <= 0) {
				request.setAttribute("noParking", "No matches");
			} else {
				request.setAttribute("parkings", parkings);
			}
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
			request.setAttribute("ParkingLotPage", true);
			request.getRequestDispatcher("resources/views/listParkinglot.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("resources/index.jsp").forward(request,response);
		}
	}

}

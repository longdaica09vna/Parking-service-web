package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.ParkinglotDAO;
import fa.training.daoImpl.ParkinglotDAOImpl;
import fa.training.model.Parkinglot;

/**
 * Servlet implementation class AddParkingServlet
 */
@WebServlet("/AddParkingServlet")
public class AddParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddParkingServlet() {
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
		ParkinglotDAO dao = new ParkinglotDAOImpl();
		String temp = request.getParameter("id");
		if(temp!=null) {
			long id = Long.parseLong(temp);
			try {
				request.setAttribute("parkingLot", dao.parkingDetail(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errorSQL", true);
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
			request.setAttribute("edit", true);
		}else {
			request.setAttribute("add", true);
		}
		request.setAttribute("ParkingLotPage", true);
		request.getRequestDispatcher("resources/views/addParking.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub\
		Parkinglot parking = null;
		try {
			ParkinglotDAOImpl dao = new ParkinglotDAOImpl();
			String raw_id = request.getParameter("id");
			String name = request.getParameter("name");
			String place = request.getParameter("place");
			String raw_price = request.getParameter("price");
			String raw_area = request.getParameter("area");
			int area = Integer.parseInt(raw_area);
			int price = Integer.parseInt(raw_price);
			long id = raw_id.equals("")?-1:Long.parseLong(raw_id);
			parking = new Parkinglot(id, area, name, place, price);
			boolean check = false;
			if(request.getParameter("button").equals("Edit")) {
				check = dao.updateParkinglot(parking);
			}else {
				check = dao.addParkinglot(parking);
			}
			
			if (check) {
				response.sendRedirect(request.getContextPath() + "/ViewParkingServlet");
			} else {
				request.setAttribute("parkingLot", parking);
				if(request.getParameter("button").equals("Edit")) {
					request.setAttribute("edit", true);
					request.setAttribute("errorEdit", true);
				}else {
					request.setAttribute("add", true);
					request.setAttribute("errorAdd", true);
				}
				request.setAttribute("ParkingLotPage", true);
				request.getRequestDispatcher("resources/views/addParking.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
	}

}

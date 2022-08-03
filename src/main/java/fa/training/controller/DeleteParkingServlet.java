package fa.training.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.CarDAO;
import fa.training.dao.ParkinglotDAO;
import fa.training.dao.TicketDAO;
import fa.training.daoImpl.CarDAOImpl;
import fa.training.daoImpl.ParkinglotDAOImpl;
import fa.training.daoImpl.TicketDAOImpl;

/**
 * Servlet implementation class DeleteParkingServlet
 */
@WebServlet("/DeleteParkingServlet")
public class DeleteParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteParkingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {			
			String raw_id=request.getParameter("id");
			int id= Integer.parseInt(raw_id);
			ParkinglotDAO dao= new ParkinglotDAOImpl();
			TicketDAO ticketDAO = new TicketDAOImpl();
			CarDAO carDAO = new CarDAOImpl();
			ticketDAO.deleteTicketByParking(id);
			carDAO.DeleteCarByParking(id);
			boolean check=dao.deleteParking(id);
			if(check) {
				response.sendRedirect("ViewParkingServlet");	
			}else {
				request.setAttribute("errorDelete", true);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}	
		
	} catch (Exception e) {
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

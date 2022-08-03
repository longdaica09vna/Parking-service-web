package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.CarDAO;
import fa.training.daoImpl.CarDAOImpl;
import fa.training.daoImpl.ParkinglotDAOImpl;

/**
 * Servlet implementation class DisplayListCarControll
 */
@WebServlet("/DisplayListCarController")
public class ViewListCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewListCarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarDAO cd=new CarDAOImpl();
		ParkinglotDAOImpl parkinglotDAO = new ParkinglotDAOImpl();	
		int page = 1;
        int records = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
		try {
			int noOfRecords = cd.getTotalRow();
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
				noOfRecords = cd.getTotalRowSearch(filter, search);
				noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
				if(noOfRecords==0) {
					noOfPages = 1;
				}
				request.setAttribute("isSearching", true);
				request.setAttribute("cars", cd.getByPage((page-1)*records, records, filter, search));
			}else{
				request.setAttribute("cars", cd.getByPage((page-1)*records, records));
			}
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        request.setAttribute("parkinglotDAO", parkinglotDAO);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}finally {
			request.setAttribute("CarPage", true);
			request.getRequestDispatcher("/resources/views/listCar.jsp").forward(request, response);
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

package fa.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fa.training.dao.EmployeeDAO;
import fa.training.daoImpl.EmployeeDAOImpl;

/**
 * Servlet implementation class ViewListEmployee
 */
@WebServlet("/ViewListEmployee")
public class ViewListEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewListEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		int page = 1;
        int records = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
		try {
			int noOfRecords = employeeDAO.getTotalRow();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
			if(noOfRecords == 0) {
				noOfPages = 1;
			}
			String search = "";
			search = request.getParameter("search")==null?"":request.getParameter("search");
			if(!search.isEmpty()) {
				String filter = request.getParameter("filter");
				request.setAttribute("filter", filter);
				request.setAttribute("search", search);
				noOfRecords = employeeDAO.getTotalRowSearch(filter, search);
				noOfPages = (int) Math.ceil(noOfRecords * 1.0 / records);
				if(noOfRecords==0) {
					noOfPages = 1;
				}
				request.setAttribute("isSearching", true);
				request.setAttribute("employees", employeeDAO.getByPage((page-1)*records, records, filter, search));
			}else{
				request.setAttribute("employees", employeeDAO.getByPage((page-1)*records, records));
			}
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
			request.setAttribute("EmployeePage", true);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorSQL", true);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		} finally {
			request.getRequestDispatcher("resources/views/listEmployee.jsp").forward(request, response);
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

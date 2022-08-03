/*
* @author HieuNT121
* @date 25 Mar 2021
*/
package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.model.Employee;

public interface EmployeeDAO {
	Employee login(String account, String password) throws SQLException;
	
	Employee getById(long id) throws SQLException;

	boolean AddEmployee(Employee employee) throws SQLException;
	
	boolean EditEmployee(Employee employee) throws SQLException;

	int getTotalRow() throws SQLException;

	List<Employee> getByPage(int offset, int fetch) throws SQLException;

	List<Employee> getByPage(int offset, int fetch, String filter, String search)
			throws SQLException;
	
	boolean DeleteEmployee(long id) throws SQLException;

	int getTotalRowSearch(String filter, String search) throws SQLException;
}

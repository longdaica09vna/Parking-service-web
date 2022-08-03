/*
* @author HieuNT121
* @date 25 Mar 2021
*/
package fa.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.EmployeeDAO;
import fa.training.model.Employee;
import fa.training.utils.DBUtils;
import fa.training.utils.SQLCommand;

public class EmployeeDAOImpl implements EmployeeDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public Employee login(String account, String password) {
		Employee employee = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_LOGIN);
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, password);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				employee = new Employee();
				employee.setEmployeeId(results.getLong("employee_ID"));
				employee.setAccount(results.getString("account"));
				employee.setDepartment(results.getString("department"));
				employee.setAddress(results.getString("address"));
				employee.setBirthdate(results.getDate("birthdate"));
				employee.setEmail(results.getString("email"));
				employee.setName(results.getString("name"));
				employee.setPhone(results.getString("phone"));
				employee.setPassword(results.getString("password"));
				employee.setSex(results.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

	@Override
	public boolean AddEmployee(Employee employee) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_ADD_EMPLOYEE);

			preparedStatement.setString(1, employee.getAccount());
			preparedStatement.setString(2, employee.getDepartment());
			preparedStatement.setString(3, employee.getAddress());
			preparedStatement.setDate(4, new java.sql.Date(employee.getBirthdate().getTime()));
			preparedStatement.setString(5, employee.getEmail());
			preparedStatement.setString(6, employee.getName());
			preparedStatement.setString(7, employee.getPhone());
			preparedStatement.setString(8, employee.getPassword());
			preparedStatement.setString(9, employee.getSex());

			check = preparedStatement.executeUpdate() == 1;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	
	@Override
	public int getTotalRow() throws SQLException {
		int value = 0;
		String query = "select count(*) from Employee";
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(query);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				value = results.getInt(1);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;

	}

	@Override
	public List<Employee> getByPage(int offset, int fetch) throws SQLException {
		Employee employee = null;
		List<Employee> employees = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_PAGE);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				employee = new Employee();
				employee.setEmployeeId(results.getInt("employee_ID"));
				employee.setAccount(results.getString("account"));
				employee.setDepartment(results.getString("department"));
				employee.setAddress(results.getString("address"));
				employee.setBirthdate(results.getDate("birthdate"));
				employee.setEmail(results.getString("email"));
				employee.setName(results.getString("name"));
				employee.setPhone(results.getString("phone"));
				employee.setPassword(results.getString("password"));
				employee.setSex(results.getString("sex"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employees;
	}

	@Override
	public List<Employee> getByPage(int offset, int fetch, String filter, String search) {
		Employee employee = null;
		List<Employee> employees = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			if (filter.equals("Name")) {
				preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_PAGE_BY_NAME);
			} else if (filter.equals("Address")) {
				preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_PAGE_BY_ADDRESS);
			} else if (filter.equals("Phone")) {
				preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_PAGE_BY_PHONE);
			} else if (filter.equals("Department")) {
				preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_PAGE_BY_DEPARTMENT);
			}
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				employee = new Employee();
				employee.setEmployeeId(results.getInt("employee_ID"));
				employee.setAccount(results.getString("account"));
				employee.setDepartment(results.getString("department"));
				employee.setAddress(results.getString("address"));
				employee.setBirthdate(results.getDate("birthdate"));
				employee.setEmail(results.getString("email"));
				employee.setName(results.getString("name"));
				employee.setPhone(results.getString("phone"));
				employee.setPassword(results.getString("password"));
				employee.setSex(results.getString("sex"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employees;
	}
	
	@Override
	public int getTotalRowSearch(String filter, String search) throws SQLException {
		int value = 0;
		connection = DBUtils.getInstance().getConnection();
		if (filter.equals("Name")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_EMPLOYEE_BY_NAME);
		} else if (filter.equals("Address")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_EMPLOYEE_BY_ADDRESS);
		} else if (filter.equals("Phone")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_EMPLOYEE_BY_PHONE);
		} else if (filter.equals("Department")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_EMPLOYEE_BY_DEPARTMENT);
		}
		try {
			search = "%" + search + "%";
			preparedStatement.setString(1, search);

			results = preparedStatement.executeQuery();
			while (results.next()) {
				value = results.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;

	}

	@Override
	public boolean EditEmployee(Employee employee) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_EDIT_EMPLOYEE);

			preparedStatement.setString(1, employee.getAccount());
			preparedStatement.setString(2, employee.getDepartment());
			preparedStatement.setString(3, employee.getAddress());
			preparedStatement.setDate(4, new java.sql.Date(employee.getBirthdate().getTime()));
			preparedStatement.setString(5, employee.getEmail());
			preparedStatement.setString(6, employee.getName());
			preparedStatement.setString(7, employee.getPhone());
			preparedStatement.setString(8, employee.getPassword());
			preparedStatement.setString(9, employee.getSex());
			preparedStatement.setLong(10, employee.getEmployeeId());

			check = preparedStatement.executeUpdate() == 1;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public boolean DeleteEmployee(long id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_EMPLOYEE);
			preparedStatement.setLong(1, id);
			check = preparedStatement.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
    }

	@Override
	public Employee getById(long id) throws SQLException {
		Employee employee = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_EMPLOYEE_BY_ID);
			preparedStatement.setLong(1, id);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				employee = new Employee();
				employee.setEmployeeId(results.getLong("employee_ID"));
				;
				employee.setAccount(results.getString("account"));
				employee.setDepartment(results.getString("department"));
				employee.setAddress(results.getString("address"));
				employee.setBirthdate(results.getDate("birthdate"));
				employee.setEmail(results.getString("email"));
				employee.setName(results.getString("name"));
				employee.setPhone(results.getString("phone"));
				employee.setPassword(results.getString("password"));
				employee.setSex(results.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

}

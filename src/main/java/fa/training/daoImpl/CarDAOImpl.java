/*
* @author HieuNT121
* @date 29 Mar 2021
*/
package fa.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.CarDAO;
import fa.training.model.Car;
import fa.training.utils.DBUtils;
import fa.training.utils.SQLCommand;

public class CarDAOImpl implements CarDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public List<Car> getAll() throws SQLException {
		Car car = null;
		List<Car> cars = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_ALL_CAR);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				car = new Car();
				car.setLicensePlate(results.getString("licensePlate"));
				car.setColor(results.getString("color"));
				car.setType(results.getString("type"));
				car.setCompany(results.getString("company"));
				car.setPark_ID(results.getLong("park_ID"));
				cars.add(car);
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
		return cars;
	}

	@Override
	public boolean addCar(Car car) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_ADD_CAR);
			preparedStatement.setString(1, car.getLicensePlate());
			preparedStatement.setString(2, car.getColor());
			preparedStatement.setString(3, car.getType());
			preparedStatement.setString(4, car.getCompany());
			preparedStatement.setLong(5, car.getPark_ID());

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
	public Car getByPlate(String id) throws SQLException {
		Car car = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_CAR_BY_PLATE);
			preparedStatement.setString(1, id);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				car = new Car();
				car.setLicensePlate(results.getString("licensePlate"));
				car.setColor(results.getString("color"));
				car.setType(results.getString("type"));
				car.setCompany(results.getString("company"));
				car.setPark_ID(results.getLong("park_ID"));
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
		return car;
	}

	@Override
	public int getTotalRow() throws SQLException {
		int value = 0;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_CAR_ROW);
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
	public int getTotalRowSearch(String filter, String search) throws SQLException {
		int value = 0;
		connection = DBUtils.getInstance().getConnection();
		if (filter.equals("License Plate")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_CAR_BY_PLATE);
		} else if (filter.equals("Color")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_CAR_BY_COLOR);
		} else if (filter.equals("Type")) {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_CAR_BY_TYPE);
		} else if (filter.equals("Company")) {
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_COUNT_CAR_BY_COMPANY);
		} else if (filter.equals("Parking lot")) {
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_COUNT_CAR_BY_PARKING_LOT);
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
	public List<Car> getByPage(int offset, int fetch) throws SQLException {
		Car car = null;
		List<Car> cars = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_CAR_PAGE);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				car = new Car();
				car.setLicensePlate(results.getString("licensePlate"));
				car.setColor(results.getString("color"));
				car.setType(results.getString("type"));
				car.setCompany(results.getString("company"));
				car.setPark_ID(results.getLong("park_ID"));
				cars.add(car);
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
		return cars;
	}

	@Override
	public List<Car> getByPage(int offset, int fetch, String filter, String search)
			throws SQLException {
		Car car = null;
		List<Car> cars = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			if (filter.equals("License Plate")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_GET_CAR_PAGE_BY_PLATE);
			} else if (filter.equals("Color")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_GET_CAR_PAGE_BY_COLOR);
			} else if (filter.equals("Type")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_GET_CAR_PAGE_BY_TYPE);
			} else if (filter.equals("Company")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_GET_CAR_PAGE_BY_COMPANY);
			} else if (filter.equals("Parking lot")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_GET_CAR_PAGE_BY_PARKING_LOT);
			}
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				car = new Car();
				car.setLicensePlate(results.getString("licensePlate"));
				car.setColor(results.getString("color"));
				car.setType(results.getString("type"));
				car.setCompany(results.getString("company"));
				car.setPark_ID(results.getLong("park_ID"));
				cars.add(car);
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
		return cars;
	}

	@Override
	public boolean editCar(Car car) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_EDIT_CAR);

			preparedStatement.setString(1, car.getColor());
			preparedStatement.setString(2, car.getType());
			preparedStatement.setString(3, car.getCompany());
			preparedStatement.setLong(4, car.getPark_ID());
			preparedStatement.setString(5, car.getLicensePlate());

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
	public boolean DeleteCar(String id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_CAR);
			preparedStatement.setString(1, id);
			check = preparedStatement.executeUpdate() == 1;
		} catch (Exception e) {
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
		return check;
	}

	@Override
	public boolean DeleteCarByParking(long id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_DELETE_CAR_BY_PARKING_ID);
			preparedStatement.setLong(1, id);
			check = preparedStatement.executeUpdate() == 1;
		} catch (Exception e) {
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
		return check;
	}

}

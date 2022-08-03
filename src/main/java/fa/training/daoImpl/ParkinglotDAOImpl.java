package fa.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.ParkinglotDAO;
import fa.training.model.Parkinglot;
import fa.training.utils.DBUtils;
import fa.training.utils.SQLCommand;

public class ParkinglotDAOImpl implements ParkinglotDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public List<Parkinglot> getParkings(int page, int pageSize) throws SQLException {
		int from = page * pageSize - (pageSize - 1);
		int to = page * pageSize;
		List<Parkinglot> parkings = new ArrayList<>();
		Parkinglot parking = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_PARKING_LOT_PAGE);
			preparedStatement.setInt(1, from);
			preparedStatement.setInt(2, to);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				parking = new Parkinglot();
				parking.setId(results.getLong("park_ID"));
				parking.setName(results.getString("name"));
				parking.setArea(results.getInt("area"));
				parking.setPlace(results.getString("place"));
				parking.setPrice(results.getInt("price"));
				parking.setStatus(results.getString("status"));
				parkings.add(parking);
			}
		} catch (SQLException e) {
			System.out.println(e + " Error:");
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
		return parkings;

	}

	@Override
	public List<Parkinglot> searchParkings(int page, int pageSize, String type, String search)
			throws SQLException {
		List<Parkinglot> parkings = new ArrayList<>();
		int from = page * pageSize - (pageSize - 1);
		int to = page * pageSize;
		Parkinglot parking = null;
		String query = "";
		if (type.equals("place")) {
			query = SQLCommand.QUERY_GET_PARKING_LOT_PAGE_BY_PLACE;
		} else if (type.equals("area")) {
			query = SQLCommand.QUERY_GET_PARKING_LOT_PAGE_BY_AREA;
		} else if (type.equals("price")) {
			query = SQLCommand.QUERY_GET_PARKING_LOT_PAGE_BY_PRICE;
		} else if (type.equals("name")) {
			query = SQLCommand.QUERY_GET_PARKING_LOT_PAGE_BY_NAME;
		}
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(query);
			search = "%" + search + "%";
			preparedStatement.setString(1, search);

			preparedStatement.setInt(2, from);
			preparedStatement.setInt(3, to);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				parking = new Parkinglot();
				parking.setId(results.getLong("park_ID"));
				parking.setName(results.getString("name"));
				parking.setArea(results.getInt("area"));
				parking.setPlace(results.getString("place"));
				parking.setPrice(results.getInt("price"));
				parking.setStatus(results.getString("status"));
				parkings.add(parking);
			}
		} catch (SQLException e) {
			System.out.println(e + " Error 3:");
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
		return parkings;

	}

	@Override
	public boolean updateParkinglot(Parkinglot parking) throws SQLException {
		boolean check = false;
		int row = 0;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_EDIT_PARKING_LOT);
			preparedStatement.setString(1, parking.getName());
			preparedStatement.setString(2, parking.getPlace());
			preparedStatement.setInt(3, parking.getArea());
			preparedStatement.setInt(4, parking.getPrice());
			preparedStatement.setLong(5, parking.getId());
			row = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + " Error:");
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
		if (row > 0) {
			check = true;
		}
		return check;
	}

	@Override
	public boolean addParkinglot(Parkinglot parking) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_ADD_PARKING_LOT);
			preparedStatement.setString(1, parking.getName());
			preparedStatement.setString(3, parking.getPlace());
			preparedStatement.setInt(2, parking.getArea());
			preparedStatement.setInt(4, parking.getPrice());
			results = preparedStatement.executeQuery();
			while (results.next()) {
				if (results.getInt(1) == 1) {
					check = true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e + " Error:");
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
	public boolean deleteParking(long id) throws SQLException {
		boolean check = false;
		int row = 0;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_DELETE_PARKING_LOT);
			preparedStatement.setLong(1, id);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + " Error:");
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
		if (row > 0) {
			check = true;
		}

		return check;
	}

	@Override
	public Parkinglot parkingDetail(long id) throws SQLException {
		Parkinglot parkinglot = new Parkinglot();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_PARKING_LOT_BY_ID);
			preparedStatement.setLong(1, id);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				parkinglot.setName(results.getString("name"));
				parkinglot.setArea(results.getInt("area"));
				parkinglot.setPlace(results.getString("place"));
				parkinglot.setPrice(results.getInt("price"));
				parkinglot.setId(results.getLong("park_ID"));
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
		return parkinglot;
	}

	@Override
	public int getTotalRow() throws SQLException {
		int value = 0;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_PARKING_LOT_ROW);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				value = results.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e + " Error:");
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
	public int getTotalRowSearch(String type, String search) throws SQLException {
		int value = 0;
		String query = "";
			if(type.equals("place")) {
				 query = SQLCommand.QUERY_COUNT_PARKING_LOT_ROW_BY_PLACE;
			}else if(type.equals("area")) {
				 query = SQLCommand.QUERY_COUNT_PARKING_LOT_ROW_BY_AREA;
			}else if(type.equals("price")) {
				 query = SQLCommand.QUERY_COUNT_PARKING_LOT_ROW_BY_PRICE;
			}else if(type.equals("name")) {
				 query = SQLCommand.QUERY_COUNT_PARKING_LOT_ROW_BY_NAME;
			}
		try {

			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(query);
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
	public List<Parkinglot> getAllParking() throws SQLException {
		List<Parkinglot> list = new ArrayList<>();
		Parkinglot parking = null;

		connection = DBUtils.getInstance().getConnection();
		preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_ALL_PARKINGLOT);
		results = preparedStatement.executeQuery();

		while (results.next()) {
			parking = new Parkinglot();
			parking.setId(results.getLong("park_ID"));
			parking.setName(results.getString("name"));
			parking.setArea(results.getInt("area"));
			parking.setPlace(results.getString("place"));
			parking.setPrice(results.getInt("price"));
			parking.setStatus(results.getString("status"));
			list.add(parking);
		}

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

		return list;
	}
}

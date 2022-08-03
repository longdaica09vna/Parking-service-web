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

import fa.training.dao.BookingOfficeDAO;
import fa.training.model.BookingOffice;
import fa.training.utils.DBUtils;
import fa.training.utils.SQLCommand;

public class BookingOfficeDAOImpl implements BookingOfficeDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public List<BookingOffice> getAll() throws SQLException {
		BookingOffice bookingOffice = null;
		List<BookingOffice> bookingOffices = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_GET_ALL_BOOKING_OFFICE);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				bookingOffice = new BookingOffice();
				bookingOffice.setOffice_ID(results.getLong("office_ID"));
				bookingOffice.setName(results.getString("name"));
				bookingOffice.setPlace(results.getString("place"));
				bookingOffice.setPhone(results.getString("phone"));
				bookingOffice.setPrice(results.getDouble("price"));
				bookingOffice.setStartContractDeadline(results.getDate("startContractDeadline"));
				bookingOffice.setEndContractDeadline(results.getDate("endContractDeadline"));
				bookingOffice.setTrip_ID(results.getLong("trip_ID"));
				bookingOffices.add(bookingOffice);
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
		return bookingOffices;
	}

	@Override
	public Boolean addBookingOffice(BookingOffice bookingOffice) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_ADD_BOOKING_OFFICE);

			preparedStatement.setString(1, bookingOffice.getName());
			preparedStatement.setString(2, bookingOffice.getPhone());
			preparedStatement.setString(3, bookingOffice.getPlace());
			preparedStatement.setDouble(4, bookingOffice.getPrice());
			preparedStatement.setDate(5,
					new java.sql.Date(bookingOffice.getStartContractDeadline().getTime()));
			preparedStatement.setDate(6,
					new java.sql.Date(bookingOffice.getEndContractDeadline().getTime()));
			preparedStatement.setLong(7, bookingOffice.getTrip_ID());
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
	public BookingOffice getByID(long id) throws SQLException {
		BookingOffice bookingOffice = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_GET_BOOKING_OFFICE_BY_ID);
			preparedStatement.setLong(1, id);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				bookingOffice = new BookingOffice();
				bookingOffice.setOffice_ID(results.getLong("office_ID"));
				bookingOffice.setName(results.getString("name"));
				bookingOffice.setPlace(results.getString("place"));
				bookingOffice.setPhone(results.getString("phone"));
				bookingOffice.setPrice(results.getDouble("price"));
				bookingOffice.setStartContractDeadline(results.getDate("startContractDeadline"));
				bookingOffice.setEndContractDeadline(results.getDate("endContractDeadline"));
				bookingOffice.setTrip_ID(results.getLong("trip_ID"));
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
		return bookingOffice;
	}

	@Override
	public List<BookingOffice> getByPage(int offset, int fetch) throws SQLException {
		BookingOffice bookingOffice = null;
		List<BookingOffice> bookingOffices = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_GET_BOOKING_OFFICE_BY_PAGE);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				bookingOffice = new BookingOffice();
				bookingOffice.setOffice_ID(results.getLong("office_ID"));
				bookingOffice.setName(results.getString("name"));
				bookingOffice.setPlace(results.getString("place"));
				bookingOffice.setPhone(results.getString("phone"));
				bookingOffice.setPrice(results.getDouble("price"));
				bookingOffice.setStartContractDeadline(results.getDate("startContractDeadline"));
				bookingOffice.setEndContractDeadline(results.getDate("endContractDeadline"));
				bookingOffice.setTrip_ID(results.getLong("trip_ID"));
				bookingOffices.add(bookingOffice);
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
		return bookingOffices;
	}

	@Override
	public List<BookingOffice> getByPage(int offset, int fetch, String filter, String search)
			throws SQLException {
		BookingOffice bookingOffice = null;
		List<BookingOffice> bookingOffices = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			if (filter.equals("Name")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_BOOKING_OFFICE_BY_NAME);
			} else {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_BOOKING_OFFICE_BY_TRIP);
			}
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				bookingOffice = new BookingOffice();
				bookingOffice.setOffice_ID(results.getLong("office_ID"));
				bookingOffice.setName(results.getString("name"));
				bookingOffice.setPlace(results.getString("place"));
				bookingOffice.setPhone(results.getString("phone"));
				bookingOffice.setPrice(results.getDouble("price"));
				bookingOffice.setStartContractDeadline(results.getDate("startContractDeadline"));
				bookingOffice.setEndContractDeadline(results.getDate("endContractDeadline"));
				bookingOffice.setTrip_ID(results.getLong("trip_ID"));
				bookingOffices.add(bookingOffice);
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
		return bookingOffices;
	}
	
	@Override
	public int getTotalRowSearch(String filter, String search) throws SQLException {
		int value = 0;
		String query = "";
			if(filter.equals("Name")) {
				 query = SQLCommand.QUERY_COUNT_BOOKING_OFFICE_BY_NAME;
			}else {
				 query = SQLCommand.QUERY_COUNT_BOOKING_OFFICE_BY_TRIP;
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
	public Boolean editBookingOffice(BookingOffice bookingOffice) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_UPDATE_BOOKING_OFFICE);

			preparedStatement.setString(1, bookingOffice.getName());
			preparedStatement.setString(2, bookingOffice.getPhone());
			preparedStatement.setString(3, bookingOffice.getPlace());
			preparedStatement.setDouble(4, bookingOffice.getPrice());
			preparedStatement.setDate(5,
					new java.sql.Date(bookingOffice.getStartContractDeadline().getTime()));
			preparedStatement.setDate(6,
					new java.sql.Date(bookingOffice.getEndContractDeadline().getTime()));
			preparedStatement.setLong(7, bookingOffice.getTrip_ID());
			preparedStatement.setLong(8, bookingOffice.getOffice_ID());
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
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_BOOKING_ROW);
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
	public Boolean deleteBookingOfficeByTrip(long id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_BOOKING_OFFICE_BY_TRIP);
			preparedStatement.setDouble(1, id);
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

}

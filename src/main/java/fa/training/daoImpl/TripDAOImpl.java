/*
* @author HieuNT121
* @date 26 Mar 2021
*/
package fa.training.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.TripDAO;
import fa.training.model.Trip;
import fa.training.utils.DBUtils;
import fa.training.utils.SQLCommand;

public class TripDAOImpl implements TripDAO{
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;
	
	@Override
	public Trip getByID(long id) throws SQLException {
		Trip trip = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_TRIP_BY_ID);
			preparedStatement.setLong(1, id);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				trip = new Trip();
				trip.setTripId(results.getLong("trip_ID"));
				trip.setBookedTicketNumber(results.getInt("bookedTicketNumber"));
				trip.setCarType(results.getString("carType"));
				trip.setDepartureDate(results.getDate("departureDate"));
				trip.setDepartureTime(results.getTime("departureTime"));
				trip.setDestination(results.getString("destination"));
				trip.setDriver(results.getString("driver"));
				trip.setMaximumOnlineTicketNumber(results.getInt("maximumOnlineTicketNumber"));
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
		return trip;
	}

	@Override
	public List<Trip> getAll() throws SQLException {
		List<Trip> trips = new ArrayList<Trip>();
		Trip trip = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_ALL_TRIP);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				trip = new Trip();
				trip.setTripId(results.getLong("trip_ID"));
				trip.setBookedTicketNumber(results.getInt("bookedTicketNumber"));
				trip.setCarType(results.getString("carType"));
				trip.setDepartureDate(results.getDate("departureDate"));
				trip.setDepartureTime(results.getTime("departureTime"));
				trip.setDestination(results.getString("destination"));
				trip.setDriver(results.getString("driver"));
				trip.setMaximumOnlineTicketNumber(results.getInt("maximumOnlineTicketNumber"));
				trips.add(trip);
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
		return trips;
	}
	
	@Override
	public List<Trip> getAllByBookedTicket() throws SQLException {
		List<Trip> trips = new ArrayList<Trip>();
		Trip trip = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_ALL_TRIP_BY_BOOKED_TICKET);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				trip = new Trip();
				trip.setTripId(results.getLong("trip_ID"));
				trip.setBookedTicketNumber(results.getInt("bookedTicketNumber"));
				trip.setCarType(results.getString("carType"));
				trip.setDepartureDate(results.getDate("departureDate"));
				trip.setDepartureTime(results.getTime("departureTime"));
				trip.setDestination(results.getString("destination"));
				trip.setDriver(results.getString("driver"));
				trip.setMaximumOnlineTicketNumber(results.getInt("maximumOnlineTicketNumber"));
				trips.add(trip);
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
		return trips;
	}
	
	public List<Trip> getAllBySearch(List<Trip> all, String search, int day, int month, int year) {
		if (all.size() == 0)
			return null;
		List<Trip> lst = new ArrayList<Trip>();
		for (Trip trip : all) {
			Date date = trip.getDepartureDate();
			String[] text = date.toString().split("-");
			if (isSearched(trip, search) && (compareDate(Integer.valueOf(text[2]), day) && compareDate(Integer.valueOf(text[1]), month)
					&& compareDate(Integer.valueOf(text[0]), year))) {
				lst.add(trip);
			}
		}
		return lst;
	}
	
	boolean isSearched(Trip trip, String search) {
		if (search.equalsIgnoreCase(""))
			return true;
		search = search.toLowerCase();
		String[] text = trip.toString().toLowerCase().split(", ");
		for (String str : text) {
			if (str.contains(search)) {
				return true;
			}
		}
		return false;
	}

	boolean compareDate(int a, int compareNum) {
		return compareNum == 0 || compareNum == a;
	}
	
	@Override
	public List<Trip> getByPage(List<Trip> searchList, int first, int records) {
		List<Trip> list = new ArrayList<Trip>();
		int total = first + records;
		total = total < searchList.size() ? total : searchList.size();
		for (int i = first; i < total; i++) {
			list.add(searchList.get(i));
		}
		return list;
	}

	@Override
	public boolean addTrip(Trip trip) throws SQLException {
		boolean check = false;

		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.TRIP_QUERY_ADD);

			preparedStatement.setString(1, trip.getCarType());
			preparedStatement.setDate(2, trip.getDepartureDate());
			preparedStatement.setTime(3, trip.getDepartureTime());
			preparedStatement.setString(4, trip.getDestination());
			preparedStatement.setString(5, trip.getDriver());
			preparedStatement.setInt(6, trip.getMaximumOnlineTicketNumber());

			check = preparedStatement.executeUpdate() != 0;
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
		return check;
	}

	@Override
	public boolean editTrip(Trip trip) throws SQLException {
		boolean check = false;

		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.TRIP_QUERY_UPDATE);

			preparedStatement.setString(1, trip.getCarType());
			preparedStatement.setDate(2, trip.getDepartureDate());
			preparedStatement.setTime(3, trip.getDepartureTime());
			preparedStatement.setString(4, trip.getDestination());
			preparedStatement.setString(5, trip.getDriver());
			preparedStatement.setInt(6, trip.getMaximumOnlineTicketNumber());
			preparedStatement.setLong(7, trip.getTrip_ID());

			check = preparedStatement.executeUpdate() != 0;
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
		return check;
	}
	
	@Override
	public boolean updateBookedTicket(long id) throws SQLException {
		boolean check = false;

		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.TRIP_QUERY_UPDATE_BOOKED_TICKET);

			preparedStatement.setLong(1, id);

			check = preparedStatement.executeUpdate() != 0;
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
		return check;
	}

	@Override
	public boolean deleteTrip(long tripId) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.TRIP_QUERY_DELETE);

			preparedStatement.setLong(1, tripId);

			check = preparedStatement.executeUpdate() != 0;
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
		return check;
	}

}

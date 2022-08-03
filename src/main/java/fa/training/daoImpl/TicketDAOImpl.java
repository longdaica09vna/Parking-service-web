/*
* @author HieuNT121
* @date 29 Mar 2021
*/
package fa.training.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.TicketDAO;
import fa.training.model.Ticket;
import fa.training.utils.DBUtils;
import fa.training.utils.SQLCommand;

public class TicketDAOImpl implements TicketDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public List<Ticket> getAll() throws SQLException {
		Ticket ticket = null;
		List<Ticket> tickets = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_ALL_TICKET);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				ticket = new Ticket();
				ticket.setTicket_ID(results.getLong("ticket_ID"));
				ticket.setBookingTime(results.getTime("bookingTime"));
				ticket.setCustomerName(results.getString("customerName"));
				ticket.setLicensePlate(results.getString("licensePlate"));
				ticket.setTrip_ID(results.getLong("trip_ID"));
				tickets.add(ticket);
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
		return tickets;
	}

	@Override
	public Boolean addTicket(Ticket ticket) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_ADD_TICKET);

			preparedStatement.setString(1, ticket.getCustomerName());
			preparedStatement.setTime(2, ticket.getBookingTime());
			preparedStatement.setString(3, ticket.getLicensePlate());
			preparedStatement.setDouble(4, ticket.getTrip_ID());
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
	public Boolean deleteTicket(long id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_TICKET);
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

	@Override
	public List<Ticket> getByPage(int offset, int fetch) throws SQLException {
		// TODO Auto-generated method stub
		Ticket ticket = null;
		List<Ticket> tickets = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_TICKET_BY_PAGE);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				ticket = new Ticket();
				ticket.setTicket_ID(results.getLong("ticket_ID"));
				ticket.setBookingTime(results.getTime("bookingTime"));
				ticket.setCustomerName(results.getString("customerName"));
				ticket.setLicensePlate(results.getString("licensePlate"));
				ticket.setTrip_ID(results.getLong("trip_ID"));
				tickets.add(ticket);
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
		return tickets;
	}

	@Override
	public List<Ticket> getByPage(int offset, int fetch, String filter, String search)
			throws SQLException {
		Ticket ticket = null;
		List<Ticket> tickets = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			if (filter.equals("Trip")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_TRIP_PAGE);
			} else if (filter.equals("License Plate")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_PLATE_PAGE);
			} else if (filter.equals("Customer Name")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_NAME_PAGE);
			}
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				ticket = new Ticket();
				ticket.setTicket_ID(results.getLong("ticket_ID"));
				ticket.setBookingTime(results.getTime("bookingTime"));
				ticket.setCustomerName(results.getString("customerName"));
				ticket.setLicensePlate(results.getString("licensePlate"));
				ticket.setTrip_ID(results.getLong("trip_ID"));
				tickets.add(ticket);
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
		return tickets;
	}

	@Override
	public List<Ticket> getByPage(int offset, int fetch, Date date) throws SQLException {
		Ticket ticket = null;
		List<Ticket> tickets = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_DATE_PAGE);
			preparedStatement.setDate(1, date);
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				ticket = new Ticket();
				ticket.setTicket_ID(results.getLong("ticket_ID"));
				ticket.setBookingTime(results.getTime("bookingTime"));
				ticket.setCustomerName(results.getString("customerName"));
				ticket.setLicensePlate(results.getString("licensePlate"));
				ticket.setTrip_ID(results.getLong("trip_ID"));
				tickets.add(ticket);
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
		return tickets;
	}

	@Override
	public List<Ticket> getByPage(int offset, int fetch, String filter, String search, Date date)
			throws SQLException {
		Ticket ticket = null;
		List<Ticket> tickets = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			if (filter.equals("Trip")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_TRIP_DATE_PAGE);
			} else if (filter.equals("License Plate")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_PLATE_DATE_PAGE);
			} else if (filter.equals("Customer Name")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_SEARCH_TICKET_BY_NAME_DATE_PAGE);
			}
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setDate(2, date);
			preparedStatement.setInt(3, offset);
			preparedStatement.setInt(4, fetch);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				ticket = new Ticket();
				ticket.setTicket_ID(results.getLong("ticket_ID"));
				ticket.setBookingTime(results.getTime("bookingTime"));
				ticket.setCustomerName(results.getString("customerName"));
				ticket.setLicensePlate(results.getString("licensePlate"));
				ticket.setTrip_ID(results.getLong("trip_ID"));
				tickets.add(ticket);
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
		return tickets;
	}

	@Override
	public Ticket getById(long id) throws SQLException {
		Ticket ticket = null;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_GET_TICKET_BY_ID);
			preparedStatement.setLong(1, id);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				ticket = new Ticket();
				ticket.setTicket_ID(results.getLong("ticket_ID"));
				ticket.setBookingTime(results.getTime("bookingTime"));
				ticket.setCustomerName(results.getString("customerName"));
				ticket.setLicensePlate(results.getString("licensePlate"));
				ticket.setTrip_ID(results.getLong("trip_ID"));
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
		return ticket;
	}
	
	@Override
	public int getTotalRow() throws SQLException {
		int value = 0;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_ROW);
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
	public Boolean deleteTicketByTrip(long id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_TICKET_BY_TRIP);
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
	
	@Override
	public Boolean deleteTicketByCar(String plate) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_TICKET_BY_TRIP);
			preparedStatement.setString(1, plate);
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
	public Boolean deleteTicketByParking(long id) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_DELETE_TICKET_BY_TRIP);
			preparedStatement.setLong(1, id);
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
	public Boolean editTicket(Ticket ticket) throws SQLException {
		boolean check = false;
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_UPDATE_TICKET);

			preparedStatement.setString(1, ticket.getCustomerName());
			preparedStatement.setTime(2, ticket.getBookingTime());
			preparedStatement.setString(3, ticket.getLicensePlate());
			preparedStatement.setDouble(4, ticket.getTrip_ID());
			preparedStatement.setLong(5, ticket.getTicket_ID());
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
	public int getTotalRowSearch(String filter, String search) throws SQLException {
		int value = 0;
		connection = DBUtils.getInstance().getConnection();
		if (filter.equals("Trip")) {
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_TRIP);
		} else if (filter.equals("License Plate")) {
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_PLATE);
		} else if (filter.equals("Customer Name")) {
			preparedStatement = connection
					.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_NAME);
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
	public int getTotalRowSearch(String filter, String search, Date date) throws SQLException {
		int value = 0;
		connection = DBUtils.getInstance().getConnection();
		
		try {
			if (filter.equals("Trip")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_TRIP_DATE);
			} else if (filter.equals("License Plate")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_PLATE_DATE);
			} else if (filter.equals("Customer Name")) {
				preparedStatement = connection
						.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_NAME_DATE);
			}
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setDate(2, date);

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
	public int getTotalRowSearch(Date date) throws SQLException {
		int value = 0;
		connection = DBUtils.getInstance().getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(SQLCommand.QUERY_COUNT_TICKET_BY_DATE);
			preparedStatement.setDate(1, date);

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

}

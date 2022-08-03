/*
* @author HieuNT121
* @date 26 Mar 2021
*/
package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.model.Trip;

public interface TripDAO {
	Trip getByID(long id) throws SQLException;
	
	List<Trip> getAll() throws SQLException;
	
	List<Trip> getAllByBookedTicket() throws SQLException;
	
	List<Trip> getByPage(List<Trip> searchList, int first, int records)throws SQLException;
	
	boolean addTrip(Trip trip) throws SQLException;
	
	boolean editTrip(Trip trip) throws SQLException;
	
	boolean deleteTrip(long tripId) throws SQLException;

	List<Trip> getAllBySearch(List<Trip> lst, String search, int day, int month, int year) throws SQLException;

	boolean updateBookedTicket(long id) throws SQLException;
}

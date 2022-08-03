/*
* @author HieuNT121
* @date 25 Mar 2021
*/
package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.model.BookingOffice;

public interface BookingOfficeDAO {
	List<BookingOffice> getAll() throws SQLException;
	
	Boolean addBookingOffice(BookingOffice bookingOffice) throws SQLException;
	
	Boolean editBookingOffice(BookingOffice bookingOffice) throws SQLException;
	
	BookingOffice getByID(long id) throws SQLException;
	
	List<BookingOffice> getByPage(int offset, int fetch) throws SQLException;
	
	List<BookingOffice> getByPage(int offset, int fetch, String filter, String search) throws SQLException;
	
	int getTotalRow() throws SQLException;

	Boolean deleteBookingOfficeByTrip(long id) throws SQLException;

	int getTotalRowSearch(String filter, String search) throws SQLException;
}

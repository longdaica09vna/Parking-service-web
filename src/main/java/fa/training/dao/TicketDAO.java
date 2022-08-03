/*
* @author HieuNT121
* @date 29 Mar 2021
*/
package fa.training.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import fa.training.model.Ticket;

public interface TicketDAO {
	List<Ticket> getAll() throws SQLException;
	
	Ticket getById(long id) throws SQLException;
	
	Boolean addTicket(Ticket ticket) throws SQLException;
	
	Boolean editTicket(Ticket ticket) throws SQLException;
	
	Boolean deleteTicket(long id) throws SQLException;
	
	List<Ticket> getByPage(int offset, int fetch) throws SQLException;
	
	List<Ticket> getByPage(int offset, int fetch, String filter, String search) throws SQLException;
	
	List<Ticket> getByPage(int offset, int fetch, Date date) throws SQLException;
	
	List<Ticket> getByPage(int offset, int fetch, String filter, String search, Date date) throws SQLException;

	int getTotalRow() throws SQLException;

	Boolean deleteTicketByTrip(long id) throws SQLException;

	Boolean deleteTicketByCar(String plate) throws SQLException;
	
	Boolean deleteTicketByParking(long id) throws SQLException;

	int getTotalRowSearch(String filter, String search) throws SQLException;

	int getTotalRowSearch(String filter, String search, Date date) throws SQLException;

	int getTotalRowSearch(Date date) throws SQLException;
}

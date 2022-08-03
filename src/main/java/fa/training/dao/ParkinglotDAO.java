/*
* @author HieuNT121
* @date 8 Apr 2021
*/
package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.model.Parkinglot;

public interface ParkinglotDAO {
	List<Parkinglot> getParkings(int page, int pageSize) throws SQLException;

	List<Parkinglot> searchParkings(int page, int pageSize, String type, String search)
			throws SQLException;

	boolean updateParkinglot(Parkinglot parking) throws SQLException;

	boolean addParkinglot(Parkinglot parking) throws SQLException;

	boolean deleteParking(long id) throws SQLException;

	Parkinglot parkingDetail(long id) throws SQLException;

	int getTotalRow() throws SQLException;

	int getTotalRowSearch(String type, String search) throws SQLException;
	
	List<Parkinglot> getAllParking() throws SQLException;
}

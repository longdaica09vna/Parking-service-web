/*
* @author HieuNT121
* @date 29 Mar 2021
*/
package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.model.Car;

public interface CarDAO {
	List<Car> getAll() throws SQLException;
	
	boolean addCar(Car car) throws SQLException;
	
	boolean editCar(Car car) throws SQLException;
	
	Car getByPlate(String id) throws SQLException;
	
	public int getTotalRow() throws SQLException;
	
	List<Car> getByPage(int offset, int fetch) throws SQLException;

	List<Car> getByPage(int offset, int fetch, String filter, String search)
			throws SQLException;
	
	boolean DeleteCar(String id) throws SQLException;

	boolean DeleteCarByParking(long id) throws SQLException;

	int getTotalRowSearch(String filter, String search) throws SQLException;
}

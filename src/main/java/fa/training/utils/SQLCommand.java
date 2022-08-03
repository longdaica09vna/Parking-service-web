package fa.training.utils;

public class SQLCommand {
	public static String QUERY_GET_ALL_BOOKING_OFFICE = "SELECT office_ID, endContractDeadline, name, phone, place, price, startContractDeadline, trip_ID FROM Bookingoffice";

	public static String QUERY_GET_EMPLOYEE_LOGIN = "SELECT employee_ID, account, department, address, birthdate, email, name, phone, password, sex FROM Employee WHERE account = ? AND password = ?";

	public static String QUERY_GET_TRIP_BY_ID = "SELECT trip_ID, bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber FROM Trip where trip_ID = ?";

	public static String QUERY_GET_TRIP_BY_DESTINATION = "SELECT trip_ID, bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber FROM Trip where destination = ?";

	public static String QUERY_GET_ALL_TRIP = "SELECT trip_ID, bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber FROM Trip";

	public static String QUERY_GET_ALL_TRIP_BY_BOOKED_TICKET = "SELECT trip_ID, bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber FROM Trip WHERE bookedTicketNumber < maximumOnlineTicketNumber";

	public static String QUERY_ADD_BOOKING_OFFICE = "INSERT INTO Bookingoffice(name, phone, place, price, startContractDeadline, endContractDeadline, trip_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";

	public static String QUERY_UPDATE_BOOKING_OFFICE = "UPDATE Bookingoffice SET name = ?, phone = ?, place = ?, price = ?, startContractDeadline = ?, endContractDeadline = ?, trip_ID = ? WHERE office_ID = ?";

	public static String QUERY_GET_BOOKING_OFFICE_BY_ID = "SELECT office_ID, endContractDeadline, name, phone, place, price, startContractDeadline, trip_ID FROM Bookingoffice WHERE office_ID = ?";

	public static String QUERY_GET_ALL_TICKET = "SELECT ticket_ID, bookingTime, customerName, licensePlate, trip_ID FROM Ticket";

	public static String QUERY_GET_TICKET_BY_ID = "SELECT ticket_ID, bookingTime, customerName, licensePlate, trip_ID FROM Ticket WHERE ticket_ID = ?";

	public static String QUERY_ADD_TICKET = "INSERT INTO Ticket(customerName, bookingTime, licensePlate, trip_ID) VALUES (?, ?, ?, ?)";

	public static String QUERY_UPDATE_TICKET = "UPDATE Ticket customerName = ?, bookingTime = ?, licensePlate = ?, trip_ID = ? WHERE ticket_ID = ?";

	public static String QUERY_GET_ALL_CAR = "SELECT licensePlate, color, type, company, park_ID FROM Car";

	public static String QUERY_GET_CAR_BY_PLATE = "SELECT licensePlate, color, type, company, park_ID FROM Car WHERE licensePlate = ?";

	public static String QUERY_DELETE_TICKET = "DELETE FROM Ticket WHERE ticket_ID = ?";

	public static String QUERY_DELETE_TICKET_BY_TRIP = "DELETE FROM Ticket WHERE trip_ID = ?";

	public static String QUERY_DELETE_TICKET_BY_PARKING = "DELETE FROM Ticket WHERE licensePlate in ( SELECT licensePlate FROM Car WHERE parkID = ?)";

	public static String QUERY_DELETE_BOOKING_OFFICE_BY_TRIP = "DELETE FROM Bookingoffice WHERE trip_ID = ?";

	public static String QUERY_DELETE_TICKET_BY_CAR = "DELETE FROM Ticket WHERE licensePlate = ?";

	public static String QUERY_GET_TICKET_BY_PAGE = "SELECT ticket_ID, bookingTime, customerName, licensePlate, trip_ID FROM Ticket order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_BOOKING_OFFICE_BY_PAGE = "SELECT office_ID, endContractDeadline, name, phone, place, price, startContractDeadline, trip_ID FROM Bookingoffice order by office_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_BOOKING_OFFICE_BY_NAME = "SELECT office_ID, endContractDeadline, name, phone, place, price, startContractDeadline, trip_ID FROM Bookingoffice WHERE name like ? order by office_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_BOOKING_OFFICE_BY_TRIP = "SELECT a.office_ID, a.endContractDeadline, a.name, a.phone, a.place, a.price, a.startContractDeadline, a.trip_ID, b.destination FROM Bookingoffice as a,Trip as b WHERE a.trip_ID=b.trip_ID and b.destination like ? order by office_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_TRIP_PAGE = "SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.destination FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID and b.destination like ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_PLATE_PAGE = "SELECT ticket_ID, bookingTime, customerName, licensePlate, trip_ID FROM Ticket WHERE licensePlate like ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_NAME_PAGE = "SELECT ticket_ID, bookingTime, customerName, licensePlate, trip_ID FROM Ticket WHERE customerName like ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_DATE_PAGE = "SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.departureDate FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID and b.departureDate = ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_TRIP_DATE_PAGE = "SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.departureDate, b.destination FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID and b.destination like ? and b.departureDate = ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_PLATE_DATE_PAGE = "SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.departureDate, b.destination FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID and a.licensePlate like ? and b.departureDate = ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_SEARCH_TICKET_BY_NAME_DATE_PAGE = "SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.departureDate, b.destination FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID and a.customerName like ? and b.departureDate = ? order by ticket_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String TRIP_QUERY_ADD = "INSERT INTO trip (bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber) VALUES (0, ?, ?, ?, ?, ? ,?)";

	public static String TRIP_QUERY_UPDATE = "UPDATE trip SET carType = ?, departureDate = ?, departureTime = ?, destination = ?, driver = ?, maximumOnlineTicketNumber = ? WHERE trip_ID = ?";

	public static String TRIP_QUERY_UPDATE_BOOKED_TICKET = "UPDATE trip SET bookedTicketNumber = bookedTicketNumber + 1 WHERE trip_ID = ?";

	public static String TRIP_QUERY_DELETE = "DELETE trip WHERE trip_ID = ? ";

	public static String QUERY_ADD_EMPLOYEE = "INSERT INTO employee (account ,department ,address ,birthdate ,email ,name ,phone ,password ,sex) VALUES(?,?,?,?,?,?,?,?,?)";

	public static String QUERY_EDIT_EMPLOYEE = "UPDATE employee SET account = ? ,department = ? ,address = ? ,birthdate =? ,email = ? ,name = ? ,phone = ?, password = ?, sex = ? WHERE employee_ID = ?";

	public static String QUERY_GET_EMPLOYEE_BY_ID = "SELECT employee_ID, account, department, address, birthdate, email, name, phone, password, sex FROM Employee WHERE employee_ID = ?";

	public static String QUERY_ADD_CAR = "INSERT INTO Car (licensePlate, color, type, company, park_ID) VALUES(?,?,?,?,?)";

	public static String QUERY_EDIT_CAR = "UPDATE Car SET color = ?,type = ?,company = ?,park_ID = ? WHERE licensePlate = ?";

	public static String QUERY_GET_ALL_PARKINGLOT = "SELECT* FROM Parkinglot";

	public static String QUERY_COUNT_BOOKING_ROW = "SELECT COUNT(*) FROM Bookingoffice";

	public static String QUERY_COUNT_CAR_ROW = "SELECT COUNT(*) FROM Car";

	public static String QUERY_GET_CAR_PAGE_BY_PLATE = "SELECT * FROM Car WHERE licensePlate LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_CAR_PAGE = "SELECT * FROM Car ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_CAR_PAGE_BY_TYPE = "SELECT * FROM Car WHERE type LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_CAR_PAGE_BY_COLOR = "SELECT * FROM Car WHERE color LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_CAR_PAGE_BY_COMPANY = "SELECT * FROM Car WHERE company LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_CAR_PAGE_BY_PARKING_LOT = "SELECT a.*,b.name FROM Car AS a, Parkinglot AS b WHERE a.park_ID = b.park_ID AND b.name LIKE ? ORDER BY licensePlate OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_DELETE_CAR = "DELETE FROM Car WHERE licensePlate = ?";

	public static String QUERY_DELETE_CAR_BY_PARKING_ID = "DELETE FROM Car WHERE park_ID = ?";

	public static String QUERY_GET_EMPLOYEE_PAGE = "SELECT * FROM Employee ORDER BY employee_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_EMPLOYEE_PAGE_BY_NAME = "SELECT * FROM Employee WHERE name LIKE ? ORDER BY employee_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_EMPLOYEE_PAGE_BY_ADDRESS = "SELECT * FROM Employee WHERE address LIKE ? ORDER BY employee_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_EMPLOYEE_PAGE_BY_PHONE = "SELECT * FROM Employee WHERE phone LIKE ? ORDER BY employee_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_GET_EMPLOYEE_PAGE_BY_DEPARTMENT = "SELECT * FROM Employee WHERE department LIKE ? ORDER BY employee_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	public static String QUERY_DELETE_EMPLOYEE = "DELETE FROM Employee WHERE employee_ID = ?";

	public static String QUERY_GET_PARKING_LOT_PAGE = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY park_ID) AS rownumber FROM Parkinglot) AS articles WHERE articles.rownumber >= ? AND articles.rownumber <=?";

	public static String QUERY_GET_PARKING_LOT_PAGE_BY_PLACE = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY park_ID) AS rownumber FROM Parkinglot WHERE place LIKE ? ) AS articles WHERE articles.rownumber >= ? AND articles.rownumber <=?";

	public static String QUERY_GET_PARKING_LOT_PAGE_BY_AREA = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY park_ID) AS rownumber FROM Parkinglot WHERE area LIKE ? ) AS articles WHERE articles.rownumber >= ? AND articles.rownumber <=?";

	public static String QUERY_GET_PARKING_LOT_PAGE_BY_PRICE = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY park_ID) AS rownumber FROM Parkinglot WHERE price LIKE ? ) AS articles WHERE articles.rownumber >= ? AND articles.rownumber <=?";

	public static String QUERY_GET_PARKING_LOT_PAGE_BY_NAME = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY park_ID) AS rownumber FROM Parkinglot WHERE name LIKE ? ) AS articles WHERE articles.rownumber >= ? AND articles.rownumber <=?";

	public static String QUERY_EDIT_PARKING_LOT = "UPDATE Parkinglot SET name = ?, place=?, area=?, price=? WHERE park_ID= ?";

	public static String QUERY_ADD_PARKING_LOT = "INSERT INTO Parkinglot(name, area, place, price) VALUES (?,?,?,?) SELECT @@ROWCOUNT";

	public static String QUERY_DELETE_PARKING_LOT = "DELETE FROM Parkinglot WHERE park_ID = ?";

	public static String QUERY_GET_PARKING_LOT_BY_ID = "SELECT name, area, place, price, park_ID FROM Parkinglot WHERE park_ID = ?";

	public static String QUERY_COUNT_PARKING_LOT_ROW = "SELECT COUNT(*) FROM Parkinglot";

	public static String QUERY_COUNT_TICKET_ROW = "SELECT COUNT(*) FROM Ticket";

	public static String QUERY_COUNT_PARKING_LOT_ROW_BY_PLACE = "SELECT COUNT(*) FROM Parkinglot WHERE place LIKE ?";

	public static String QUERY_COUNT_PARKING_LOT_ROW_BY_AREA = "SELECT COUNT(*) FROM Parkinglot WHERE area LIKE ?";

	public static String QUERY_COUNT_PARKING_LOT_ROW_BY_PRICE = "SELECT COUNT(*) FROM Parkinglot WHERE price LIKE ?";

	public static String QUERY_COUNT_PARKING_LOT_ROW_BY_NAME = "SELECT COUNT(*) FROM Parkinglot WHERE name LIKE ?";

	public static String QUERY_COUNT_BOOKING_OFFICE_BY_NAME = "SELECT COUNT(*) FROM Bookingoffice WHERE name LIKE ?";

	public static String QUERY_COUNT_BOOKING_OFFICE_BY_TRIP = "SELECT COUNT(*) FROM Bookingoffice WHERE trip LIKE ?";

	public static String QUERY_COUNT_CAR_BY_PLATE = "SELECT COUNT(*) FROM Car WHERE licensePlate LIKE ?";

	public static String QUERY_COUNT_CAR_BY_COLOR = "SELECT COUNT(*) FROM Car WHERE color LIKE ?";

	public static String QUERY_COUNT_CAR_BY_TYPE = "SELECT COUNT(*) FROM Car WHERE type LIKE ?";

	public static String QUERY_COUNT_CAR_BY_COMPANY = "SELECT COUNT(*) FROM Car WHERE company LIKE ?";

	public static String QUERY_COUNT_CAR_BY_PARKING_LOT = "SELECT COUNT(*) FROM (SELECT a.*,b.name FROM Car AS a, Parkinglot AS b WHERE a.park_ID = b.park_ID) as c WHERE c.name LIKE ?";

	public static String QUERY_COUNT_EMPLOYEE_BY_NAME = "SELECT COUNT(*) FROM Employee WHERE name LIKE ?";

	public static String QUERY_COUNT_EMPLOYEE_BY_ADDRESS = "SELECT COUNT(*) FROM Employee WHERE address LIKE ?";

	public static String QUERY_COUNT_EMPLOYEE_BY_PHONE = "SELECT COUNT(*) FROM Employee WHERE phone LIKE ?";

	public static String QUERY_COUNT_EMPLOYEE_BY_DEPARTMENT = "SELECT COUNT(*) FROM Employee WHERE department LIKE ?";

	public static String QUERY_COUNT_TICKET_BY_TRIP = "SELECT COUNT(*) FROM (SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.destination FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID)AS c WHERE c.destination LIKE ?";

	public static String QUERY_COUNT_TICKET_BY_PLATE = "SELECT COUNT(*) FROM Employee WHERE licensePlate LIKE ?";

	public static String QUERY_COUNT_TICKET_BY_NAME = "SELECT COUNT(*) FROM Employee WHERE customerName LIKE ?";

	public static String QUERY_COUNT_TICKET_BY_DATE = "SELECT COUNT(*) FROM (SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.departureDate FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID)AS c WHERE c.departureDate = ?";
	
	public static String QUERY_COUNT_TICKET_BY_TRIP_DATE = "SELECT COUNT(*) FROM (SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.destination, b.departureDate FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID)AS c WHERE c.destination LIKE ? AND c.departureDate = ?";

	public static String QUERY_COUNT_TICKET_BY_PLATE_DATE = "SELECT COUNT(*) FROM (SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.destination, b.departureDate FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID)AS c WHERE c.licensePlate LIKE ? AND c.departureDate = ?";
	
	public static String QUERY_COUNT_TICKET_BY_NAME_DATE = "SELECT COUNT(*) FROM (SELECT a.ticket_ID, a.bookingTime, a.customerName, a.licensePlate, a.trip_ID, b.destination, b.departureDate FROM Ticket as a, Trip as b WHERE a.trip_ID=b.trip_ID)AS c WHERE c.customerName LIKE ? AND c.departureDate = ?";
}

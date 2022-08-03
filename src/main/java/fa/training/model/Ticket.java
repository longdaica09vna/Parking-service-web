/*
* @author HieuNT121
* @date 29 Mar 2021
*/
package fa.training.model;

import java.sql.Time;

public class Ticket {
	private long ticket_ID;
	private Time bookingTime;
	private String customerName;
	private String licensePlate;
	private long trip_ID;
	
	public Ticket() {
		super();
	}

	public Ticket(long ticket_ID, Time bookingTime, String customerName, String licensePlate,
			long trip_ID) {
		super();
		this.ticket_ID = ticket_ID;
		this.bookingTime = bookingTime;
		this.customerName = customerName;
		this.licensePlate = licensePlate;
		this.trip_ID = trip_ID;
	}

	public long getTicket_ID() {
		return ticket_ID;
	}

	public void setTicket_ID(long ticket_ID) {
		this.ticket_ID = ticket_ID;
	}

	public Time getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public long getTrip_ID() {
		return trip_ID;
	}

	public void setTrip_ID(long trip_ID) {
		this.trip_ID = trip_ID;
	}
	
	
}

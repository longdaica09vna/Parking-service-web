/*
* @author HieuNT121
* @date 25 Mar 2021
*/
package fa.training.model;

import java.util.Date;

public class BookingOffice {
	private long office_ID;
	private String name;
	private String phone;
	private String place;
	private double price;
	private Date startContractDeadline;
	private Date endContractDeadline;
	private long trip_ID;
	
	public BookingOffice() {
		super();
	}

	public BookingOffice(long office_ID, String name, String phone, String place, double price,
			Date startContractDeadline, Date endContractDeadline, long trip_ID) {
		super();
		this.office_ID = office_ID;
		this.name = name;
		this.phone = phone;
		this.place = place;
		this.price = price;
		this.startContractDeadline = startContractDeadline;
		this.endContractDeadline = endContractDeadline;
		this.trip_ID = trip_ID;
	}

	public long getOffice_ID() {
		return office_ID;
	}

	public void setOffice_ID(long office_ID) {
		this.office_ID = office_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartContractDeadline() {
		return startContractDeadline;
	}

	public void setStartContractDeadline(Date startContractDeadline) {
		this.startContractDeadline = startContractDeadline;
	}

	public Date getEndContractDeadline() {
		return endContractDeadline;
	}

	public void setEndContractDeadline(Date endContractDeadline) {
		this.endContractDeadline = endContractDeadline;
	}

	public long getTrip_ID() {
		return trip_ID;
	}

	public void setTrip_ID(long trip_ID) {
		this.trip_ID = trip_ID;
	}
	
	
}

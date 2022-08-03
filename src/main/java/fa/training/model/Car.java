/*
* @author HieuNT121
* @date 29 Mar 2021
*/
package fa.training.model;

public class Car {
	private String licensePlate;
	private String color;
	private String type;
	private String company;
	private long park_ID;
	
	public Car() {
		super();
	}

	public Car(String licensePlate, String color, String type, String company, long park_ID) {
		super();
		this.licensePlate = licensePlate;
		this.color = color;
		this.type = type;
		this.company = company;
		this.park_ID = park_ID;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getPark_ID() {
		return park_ID;
	}

	public void setPark_ID(long park_ID) {
		this.park_ID = park_ID;
	} 
	
	
}

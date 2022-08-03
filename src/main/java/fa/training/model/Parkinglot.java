package fa.training.model;

public class Parkinglot {
	private long id;
	private int area;
	private String name;
	private String place;
	private int price;
	private String status;
	
	
	public Parkinglot(long id, int area, String name, String place, int price) {
		super();
		this.id = id;
		this.area = area;
		this.name = name;
		this.place = place;
		this.price = price;
	}
	public Parkinglot(int area, String name, String place, int price) {
		super();
		this.area = area;
		this.name = name;
		this.place = place;
		this.price = price;
	}
	public Parkinglot(long id, int area, String name, String place, int price, String status) {
		super();
		this.id = id;
		this.area = area;
		this.name = name;
		this.place = place;
		this.price = price;
		this.status = status;
	}
	public Parkinglot() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

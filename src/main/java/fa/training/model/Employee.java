/*
* @author HieuNT121
* @date 23 Mar 2021
*/
package fa.training.model;

import java.util.Date;

public class Employee {
	private long employeeId;
	private String account;
	private String department;
	private String address;
	private Date birthdate;
	private String email;
	private String name;
	private String phone;
	private String password;
	private String sex;
	
	public Employee() {
		super();
	}

	public Employee(long employeeId, String account, String department, String address,
			Date birthdate, String email, String name, String phone, String password, String sex) {
		super();
		this.employeeId = employeeId;
		this.account = account;
		this.department = department;
		this.address = address;
		this.birthdate = birthdate;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.sex = sex;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	
	
}

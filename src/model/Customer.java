package model;

public class Customer {
	
	public Customer() {
		super();
	}
	public Customer(int id, String user_name, String full_name, String phone_number, String email, String password, String address) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.full_name = full_name;
		this.phone_number = phone_number;
		this.email = email;
		this.password = password;
		this.address = address;

	}
	
	private int id;
	private String user_name;
	private String full_name;
	private String phone_number;
	private String email;
	private String password;
	private String address;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", user_name=" + user_name + ", full_name=" + full_name + ", phone_number="
				+ phone_number + ", email=" + email + ", password=" + password + ", address=" + address + "]";
	}

}


public class User {
	// customer id
	private int id;
	// customer real name
	private String fullName;
	// user name in system
	private String userName;
	// customer's phone number
	private String phoneNumber;
	// customer's email
	private String email;
	// customer password
	private String password;
	// customer address
	private String address;
	// card number
	private String cardNumber;

	// User account constructor
	public User(String fullName, String address, String cardNumber, String phoneNumber, String email) {
		this.fullName = fullName;
		this.address = address;
		this.cardNumber = cardNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	// User constructor
	public User(int id, String fullName, String userName, String password) {
		this.id = id;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;

		this.address = "";
		this.phoneNumber = "";
		this.cardNumber = "";
		this.email = "";
	}

	public int getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUserName() {
		return userName;
	}

	public String getAddress() {
		return address;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.userName = userName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
}

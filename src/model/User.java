package model;

public class User {
	
    public User() {
    	super();
    }
    
    public User(int id, String username, String password, String role) {
    	super();
    	this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
 
    public User(int id, String username, String password) {
    	super();
    	this.id = id;
        this.username = username;
        this.password = password;
        this.role = "Customer";
    }
    
	private int id;
    private String username;
    private String password;
    private String role; // Role: "Manager" or "Customer"
    
    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Check if the credentials match
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    public void setId(int id) {
    	this.id = id;
    }
	public int getId() {
		return id;
	}
	public String toString() {
		return id+" "+username+" "+password+" "+role ;
	}
}

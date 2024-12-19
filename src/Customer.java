
public class Customer extends User {
    private Cart cart;
    public Customer(String fullName, String address, String cardNumber, String phoneNumber, String email){
		super(fullName, address, cardNumber, phoneNumber, email);
	}

    public Customer(int id, String fullName, String userName, String password) {
		super(id, fullName, userName, password);
		if(cart == null){
			this.cart = new Cart();	
		}
	}
    public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}

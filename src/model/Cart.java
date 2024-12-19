package model;

public class Cart {
    public Cart() {
		super();
	}
	public Cart(int id, int customer_id, int product_id, int quantity) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}
	private int id;
    private int customer_id;
    private int product_id;
    private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer_id=" + customer_id + ", product_id=" + product_id + ", quantity="
				+ quantity + "]";
	}
}

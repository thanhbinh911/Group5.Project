package model;

public class Order {

	public Order() {
		super();
	}
	public Order(int id, int customer_id, String order_status) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.order_status = order_status;
	}
	private int id;
	private int customer_id;
	private String order_status;
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
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", order_status=" + order_status + "]";
	}
}
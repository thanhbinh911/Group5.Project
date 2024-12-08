package model;

public class Product {
	public Product() {
		super();
	}
	public Product(int id, String product_name, int price, String product_status, int quantity, String category, String product_description) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.price = price;
		this.product_status = product_status;
		this.quantity = quantity;
		this.category = category;
		this.product_description = product_description;
	}
	private int id;
	private String product_name;
	private int price;
	private String product_status;
	private int quantity;
	private String category;
	private String product_description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", product_name=" + product_name + ", price=" + price + ", product_status="
				+ product_status + ", quantity=" + quantity + ", category=" + category + ", product_description="
				+ product_description + "]";
	}
	
}
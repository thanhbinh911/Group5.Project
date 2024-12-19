package model;

public class category_detail {
	public category_detail() {
		super();
	}
	public category_detail(int id, int category_id, int product_id) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.product_id = product_id;
	}
	private int id;
	private int category_id;
	private int product_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	@Override
	public String toString() {
		return "category_detail [id=" + id + ", category_id=" + category_id + ", product_id=" + product_id + "]";
	}
}

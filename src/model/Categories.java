package model;

public class Categories {
	public Categories() {
		super();
	}
	public Categories(int id, String category_name) {
		super();
		this.id = id;
		this.category_name = category_name;
	}
	private int id;
	private String category_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	@Override
	public String toString() {
		return "Categories [id=" + id + ", category_name=" + category_name + "]";
	}
}

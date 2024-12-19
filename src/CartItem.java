
public class CartItem {

	public Product product;
	
	public int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public void add(){
		add(1);
	}
	
	public void add(float quantity){
		this.quantity += quantity;
	}
	
	@Override
	public String toString(){
		return this.product.getName() + FileHandler.SPLIT_COMMA + this.quantity;
	}
}

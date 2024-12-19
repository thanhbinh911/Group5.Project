
import java.util.ArrayList;
import java.util.List;

public class Cart {
	private ArrayList<CartItem> items = new ArrayList<>();

    public void add(Product p, int quantity){
		for(CartItem ci : items){
			if(ci.product.equals(p)){
				ci.quantity += quantity;
				return;
			}
		}
		add(new CartItem(p, quantity));
    }

    public void add(CartItem item){
		items.add(item);
	}

    public List<CartItem> getList(){
		return items;
	}

    public void clear(){
		items.clear();
	}

    public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}

    public void remove(CartItem item){
		items.remove(item);
	}
}

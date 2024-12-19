
import java.util.ArrayList;
import java.util.List;

public class Order {

	private int orderId;
	private String customerId;
	private String orderedDate;
	private List<CartItem> orderItem;
	
	public Order(){
		orderItem = new ArrayList<CartItem>();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public List<CartItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<CartItem> orderItem) {
		this.orderItem = orderItem;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}

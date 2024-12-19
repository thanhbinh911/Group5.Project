
import java.util.HashMap;
import java.util.List;

public interface Model {
	public List<Product> getProducts();
	public boolean login(String userName, String password, String loginIdentity);
	public boolean signup(String userName, String password);
	public double getPrice(Cart cart);
	public Customer getUserInfo(String userName);
	public boolean setUserInfo(String userName, Customer info);
    public int processOrder(String currentUserID, Cart cart);
	
	public HashMap<String, Customer> getCustomerList();
	
	public List<Product> getProductList();

	public List<AdminReport> generateReport(String productName, String start, String end);
}

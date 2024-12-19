
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DummyModel implements Model {
	ArrayList<Product> productList = new ArrayList<>();
	HashMap<String, String> passwords = new HashMap<>();
	HashMap<String, Customer> customerList = new HashMap<>();
	HashMap<String, Admin> adminList = new HashMap<>();

	List<Order> orderList = new ArrayList<Order>();

	public DummyModel() {
		loadData();
	}

	private void loadData() {
		loadProductData();
		loadCusteomerData();
		loadAdminData();
		orderList = loadOrderList();
	}

	private void loadAdminData() {
		String adminData = FileHandler.readFromFile(FileHandler.ADMIN_DATA);
		String[] adminArray = adminData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < adminArray.length; i++) {
			String[] customers = adminArray[i].split(FileHandler.SPLIT_COMMA);
			String idStr = customers[0].trim();
			int id = Integer.parseInt(idStr.trim());
			String name = customers[1];
			String username = customers[2];
			String password = customers[3];
			Admin c = new Admin(id, name, username, password);
			adminList.put(username, c);
			passwords.put(username, password);
		}
	}

	private void loadElectronicsData() {
		String electronicsData = FileHandler.readFromFile(FileHandler.ELECTRONICS_FILE);
		String[] electronicsArray = electronicsData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < electronicsArray.length; i++) {
			String[] game = electronicsArray[i].split(FileHandler.SPLIT_COMMA);
			String name = game[0];
			String price = game[1];
			String quantity = game[2];
            String description = game[3];
			Electronics c = new Electronics(ProductType.ELECTRONICS, name,
					Float.parseFloat(price), description,
					Integer.parseInt(quantity));
			productList.add(c);
		}
	}

	private void loadFashionData() {
		String fashionData = FileHandler.readFromFile(FileHandler.FASHION_FILE);
		String[] fashionArray = fashionData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < fashionArray.length; i++) {
			String[] fashion = fashionArray[i].split(FileHandler.SPLIT_COMMA);
			String name = fashion[0];
			float price = Float.parseFloat(fashion[1]);
			int quantity = Integer.parseInt(fashion[2]);
			String description = fashion[3];
			Fashion c = new Fashion(ProductType.FASHION, name, price, description, quantity);
			productList.add(c);
		}
	}

	private void loadHomeandfurnitureData() {
		String homeandfurnitureData = FileHandler.readFromFile(FileHandler.HOMEANDFURNITURE_FILE);
		String[] homeandfurnitureArray = homeandfurnitureData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < homeandfurnitureArray.length; i++) {
			String[] homeandfurniture = homeandfurnitureArray[i].split(FileHandler.SPLIT_COMMA);
			String name = homeandfurniture[0];
			float price = Float.parseFloat(homeandfurniture[1]);
			int quantity = Integer.parseInt(homeandfurniture[2]);
			String description = homeandfurniture[3];
			Homeandfurniture tvObj = new Homeandfurniture(ProductType.HOMEANDFURNITURE, name, price, description, quantity);
			productList.add(tvObj);
		}
	}

    private void loadSportsandbooksData() {
		String sportsandbooksData = FileHandler.readFromFile(FileHandler.SPORTSANDBOOKS_FILE);
		String[] sportsandboksArray = sportsandbooksData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < sportsandboksArray.length; i++) {
			String[] sportsandbooks = sportsandboksArray[i].split(FileHandler.SPLIT_COMMA);
			String name = sportsandbooks[0];
			float price = Float.parseFloat(sportsandbooks[1]);
			int quantity = Integer.parseInt(sportsandbooks[2]);
			String description = sportsandbooks[3];
			Sportsandbooks tvObj = new Sportsandbooks(ProductType.SPORTSANDBOOKS, name, price, description, quantity);
			productList.add(tvObj);
		}
	}

	private void loadProductData() {
		loadElectronicsData();
		loadFashionData();
		loadHomeandfurnitureData();
		loadSportsandbooksData();
	}

	private void loadCusteomerData() {
		loadCustomerDetail();
		loadCustomerCart();
	}

	private void loadCustomerDetail() {
		String customerData = FileHandler
				.readFromFile(FileHandler.CUSTOMER_DATA);
		String[] customerArray = customerData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < customerArray.length; i++) {
			String[] customers = customerArray[i]
					.split(FileHandler.SPLIT_COMMA);
			String idStr = customers[0].trim();
			int id = Integer.parseInt(idStr.trim());
			String fullName = customers[1];
			String username = customers[2];
			String password = customers[3];
			String phoneNumber = customers[4];
			String address = customers[5];
			String cardNumber = customers[6];
			Customer c = new Customer(id, fullName, username, password);
			c.setPhoneNumber(phoneNumber);
			c.setAddress(address);
			c.setCardNumber(cardNumber);
			customerList.put(username, c);
			passwords.put(username, password);
		}
	}

	private void loadCustomerCart() {
		String cartData = FileHandler.readFromFile(FileHandler.CART_FILE);
		String[] cartArray = cartData.split(FileHandler.SPLIT_CEMI);
		for (int i = 0; i < cartArray.length; i++) {
			String[] cartOfCustomer = cartArray[i]
					.split(FileHandler.SPLIT_COMMA);
			String userId = cartOfCustomer[0];
			Cart cart = customerList.get(userId).getCart();
			for (int j = 1; j < cartOfCustomer.length; j = j + 2) {
				Product p = getProductByName(cartOfCustomer[j]);
				int productQuantity = Integer.parseInt(cartOfCustomer[j + 1]);
				CartItem ct = new CartItem(p, productQuantity);
				cart.add(ct);
			}
		}
	}

	private Product getProductByName(String name) {
		for (Product p : productList) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Product> getProducts() {
		return productList;
	}

	@Override
	public boolean login(String username, String password, String loginIdentity) {
		if (loginIdentity.equals("CUSTOMER")) {
			Customer loginUser = customerList.get(username);
			if (loginUser != null && password.equals(loginUser.getPassword())) {
				return true;
			}
		} else {
			Admin loginAdmin = adminList.get(username);
			if (loginAdmin != null && password.equals(loginAdmin.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean signup(String username, String password) {
		if (passwords.containsKey(username)) {
			return false;
		}
		passwords.put(username, password);
		customerList.put(username, new Customer("", username, "", "", ""));
		return true;
	}

	@Override
	public Customer getUserInfo(String username) {
		return customerList.get(username);
	}

	@Override
	public boolean setUserInfo(String username, Customer info) {
		customerList.put(username, info);
		return true;
	}

	@Override
	public double getPrice(Cart cart) {
		double total = 0;
		for (CartItem item : cart.getList()) {
			if (item.product.hasProperty("price")) {
				total += ((double) item.product.getPropertyValue("price"))
						* item.quantity;
			}
		}
		return total;
	}

	@Override
	public int processOrder(String currentUserID, Cart cart) {
		for (CartItem ct : cart.getList()) {
			int productQuantity = ct.product.getQuantity();
			if (productQuantity < ct.quantity) {
				return 1;
			}
			int currentProductQuantity = (int) (productQuantity - ct.quantity);
			ct.product.setQuantity(currentProductQuantity);
		}
		saveProductData();
		saveOrderData(currentUserID, cart);
		return 0;
	}

	public void saveOrderData(String currentUserID, Cart cart) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateStr = dateFormat.format(date);
		int orderid = orderList.size() + 1;

		Order order = new Order();
		order.setCustomerId(currentUserID);
		order.setOrderItem(cart.getList());
		order.setOrderedDate(dateStr);
		order.setOrderId(orderid);
		orderList.add(order);

		String orderData = orderid + FileHandler.SPLIT_COMMA + currentUserID
				+ FileHandler.SPLIT_COMMA + dateStr + FileHandler.SPLIT_COMMA;
		for (CartItem ct : cart.getList()) {
			orderData += ct.toString() + FileHandler.SPLIT_COMMA;
		}
		orderData += "\n";
		FileHandler.appendToFile(orderData, FileHandler.ORDER_FILE);
	}

	public void saveProductData() {
		String electronicsData = "";
		String fashionData = "";
		String homeandfurnitureData = "";
		String sportsandbooksData = "";
		for (Product p : productList) {
			switch (p.getType()) {
			case ELECTRONICS:
				electronicsData += p.toString() + "\n";
				break;
			case SPORTSANDBOOKS:
				sportsandbooksData += p.toString() + "\n";
				break;
			case FASHION:
				fashionData += p.toString() + "\n";
				break;
			case HOMEANDFURNITURE:
			    homeandfurnitureData += p.toString() + "\n";
			default:
				break;
			}
		}
		FileHandler.writeToFile(homeandfurnitureData, FileHandler.ELECTRONICS_FILE);
		FileHandler.writeToFile(fashionData, FileHandler.FASHION_FILE);
		FileHandler.writeToFile(homeandfurnitureData, FileHandler.HOMEANDFURNITURE_FILE);
		FileHandler.writeToFile(sportsandbooksData, FileHandler.SPORTSANDBOOKS_FILE);
	}

	@Override
	public HashMap<String, Customer> getCustomerList() {
		return customerList;
	}

	@Override
	public List<Product> getProductList() {
		return productList;
	}

	private List<Order> loadOrderList() {
		List<Order> orders = new ArrayList<Order>();
		String orderData = FileHandler.readFromFile(FileHandler.ORDER_FILE);
		String[] orderArray = orderData.split(FileHandler.SPLIT_CEMI);
		Order oo;
		for (int i = 0; i < orderArray.length; i++) {
			oo = new Order();
			String[] order = orderArray[i].split(FileHandler.SPLIT_COMMA);
			int orderId = Integer.parseInt(order[0]);
			String customerId = order[1];
			String orderedDate = order[2].substring(0, 10);
			// Set attributes into Order object
			oo.setOrderId(orderId);
			oo.setCustomerId(customerId);
			oo.setOrderedDate(orderedDate);
			// Assemble order's cartItem data
			for (int j = 3; j < order.length; j = j + 2) {
				String pname = order[j];
				Product p = getProductByName(pname);
				int pquantity = Integer.parseInt(order[j + 1]);
				CartItem ci = new CartItem(p, pquantity);
				oo.getOrderItem().add(ci);
			}
			orders.add(oo);
		}
		return orders;
	}

	@Override
	public List<AdminReport> generateReport(String productName, String start,
			String end) {
		List<AdminReport> reports = new ArrayList<AdminReport>();
		AdminReport ar = null;
		for (Order order : loadOrderList()) {
			String orderedDate = order.getOrderedDate();
			List<CartItem> cartItems = order.getOrderItem();
			for (CartItem ct : cartItems) {
				if (ct.product.getName().equals(productName)) {
					boolean isAdd = false;
					if (Utility.isEmpty(start) && Utility.isEmpty(end)) {
						isAdd = true;
					} else if (Utility.isEmpty(start) && !Utility.isEmpty(end)) {
						if(orderedDate.compareTo(end) <= 0){
							isAdd = true;
						}
					}else if (Utility.isEmpty(end) && !Utility.isEmpty(start)){
						if(orderedDate.compareTo(start) >= 0){
							isAdd = true;
						}
					}else if(orderedDate.compareTo(start) >= 0
							&& orderedDate.compareTo(end) <= 0){
						isAdd = true;
					}
					if (isAdd) {
						ar = new AdminReport();
						String customerId = order.getCustomerId();
						ar.setCustomerId(customerId);
						ar.setCustomerName(getUserInfo(customerId).getFullName());
						ar.setOrderId(order.getOrderId() + "");
						ar.setOrderedDate(orderedDate);
						ar.setItemName(ct.product.getName());
						ar.setQuantityOfItem(ct.quantity + "");
						reports.add(ar);
					}
				}

			}
		}
		return reports;
	}

}
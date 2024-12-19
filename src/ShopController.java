
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShopController {

	public static ImageIcon NO_IMAGE_ICON = generateIcon("https://placeholdit.imgix.net/~text?txtsize=23&bg=ffffff&txtclr=000000&txt=No+Image&w=200&h=200", 150, 150);

	public static ImageIcon LOGO_ICON = generateIcon("https://placeholdit.imgix.net/~text?txtsize=23&bg=ffffff&txtclr=000000&txt=No+Image&w=200&h=200", 150, 150);
	public static ImageIcon SPORTSANDBOOKS_ICON = generateIcon("https://pbs.twimg.com/profile_images/743536681706364929/X2qPv11V.jpg", 150, 150);
	public static ImageIcon FASHION_ICON = generateIcon("https://upload.wikimedia.org/wikipedia/en/9/92/The_Angry_Birds_Movie_Soundtrack.jpg", 150, 150);
	public static ImageIcon HOMEANDFURNITURE_ICON = generateIcon("http://shortyawards.com.s3.amazonaws.com/entries/8th/18679295525_f39cc1bc70_z.jpg", 150, 150);
	public static ImageIcon ELECTRONICS_ICON = generateIcon("https://content.halocdn.com/media/Default/games/halo-spartan-assault/Page/game_overview_thumbnail_halospartanassault-9f927d8913d5434499e8d31a0d4c88e7.jpg", 150, 150);
	
	public static HashMap<String, ImageIcon> IMAGE_CACHE;
	
	public static ImageIcon generateIcon(String imgLoc, int width, int height){
		if(IMAGE_CACHE == null)  IMAGE_CACHE = new HashMap<String, ImageIcon>();
		if(IMAGE_CACHE.containsKey(imgLoc)) return IMAGE_CACHE.get(imgLoc);
		try {
			URL url = new URL(imgLoc);
			ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			IMAGE_CACHE.put(imgLoc, icon);
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private JFrame window = new JFrame();
	private Model backend;
	
	private String currentUserID;

	public ShopController(Model b){
		this.backend = b;
	}
	
	public void setView(View view){
		view.setController(this);
		view.initialize();
		window.setContentPane(view);
		window.revalidate();
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public Model getBackend(){
		return this.backend;
	}
	
	public void init(){
		window.setResizable(false);
		window.setTitle("Online Shop");
		window.setSize(900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		this.setView(new LoginView());
	}
	
	public void showPopup(String message){
		JOptionPane.showMessageDialog(window, message);
	}
	
	public Customer getCurrentCustomerDetails(){
		return getBackend().getUserInfo(currentUserID);
	}
	
	public void signup(String username, String pass, String confPass){
		
		// Ensuring length
		if(username.length() < 3) {
			showPopup("Your user ID must be at least 3 chars long!");
			return;
		}
		else if(pass.length() < 5){
			showPopup("Your password must be at least 5 chars long!");
			return;
		}
		else if(!pass.equals(confPass)){
			showPopup("The passwords do not match");
			return;
		}
		
		boolean success = getBackend().signup(username, pass);
		
		if(!success){
			showPopup("Signup failed, that userID may already be in use!");
		} else {
			showPopup("Your account has been created, please edit your details by clicking 'My account' in the top right.");
			attemptLogin(username, pass, "CUSTOMER");
		}
	}

	public void attemptLogin(String username, String password, String loginIdentity){
		if(backend.login(username, password,loginIdentity)){
			currentUserID = username;
			// If customer logged in
			if(loginIdentity.equals("CUSTOMER")){
				showProductList();
			}else{
				showAdminProductList();
			}
			
		} else {
			showPopup("Login failed! Please ensure that your user ID and password are correct.");
		}
	}
	
	public void updateUserDetails(Customer c){
		if(this.currentUserID != null){
			boolean success = getBackend().setUserInfo(this.currentUserID, c);
			if(!success){
				showPopup("There was an error saving your information! Please try again later.");
			}
		} else {
			System.err.println("Can't update user info, no one is signed in!");
		}
	}

	public void showCheckout(){
		ConfirmDialog.display(this);
	}
	
	public Cart getCart(){
		return getCurrentCustomerDetails().getCart();
	}
	
	public void addToCart(Product p, int quantity){
		getCurrentCustomerDetails().getCart().add(p, quantity);
	}
	
	public void showCartView(){
		setView(new CartView());
	}
	
	public double getTotalCartPrice(){
		return getBackend().getPrice(getCurrentCustomerDetails().getCart());
	}

	public void showProductList() {
		setView(new ProductListView());
	}
	
	public void showAdminProductList(){
		setView(new AdminProductListView());
	}

	public void attemptTransaction() {
		Customer c = getBackend().getUserInfo(currentUserID);
		String prefix = "Order failed! ";
		if(c.getFullName().trim().equals("")){
			showPopup(prefix + "You have not entered your full name!");
			return;
		}
		else if(c.getAddress().trim().equals("")){
			showPopup(prefix + "You have not entered your home address!");
			return;
		}
		else if(c.getPhoneNumber().trim().equals("")){
			showPopup(prefix + "You have not entered your phone number!");
			return;
		}
		else if(c.getCardNumber().trim().equals("")){
			showPopup(prefix + "You have not entered your card number!");
			return;
		}
		
		int success = getBackend().processOrder(currentUserID, getCurrentCustomerDetails().getCart());
		
		String wrongMsg = "Sorry, your order could not be placed! ";
		if( success == 1 ){
			showPopup(wrongMsg +"Product quantity is larger than stock.");
		}else if (success == 2){
			showPopup(wrongMsg + "Please ensure that all of your information is correct.");
		}
		else {
			showPopup("Your order has been placed successfully! Have a nice day!");
			getCurrentCustomerDetails().getCart().clear();
			this.showCartView();
		}
	}
	
	public void storeCartData(){
		String cartData = "";
		for(Entry<String, Customer> entry : getBackend().getCustomerList().entrySet()) {
		        Customer customer = entry.getValue();
		        cartData += entry.getKey();
		        Cart cart = customer.getCart();
		        for (CartItem ct: cart.getList()){
		        	cartData += FileHandler.SPLIT_COMMA + ct.toString();
		        }
		        cartData += "\n";
		    }
		FileHandler.writeToFile(cartData, FileHandler.CART_FILE);
	}
	
	public void addProduct(Product p){
		this.backend.getProductList().add(p);
	}
	
	public void showAdminReportView(){
		setView(new AdminReportView());
	}
	
	public List<AdminReport> generateReport(String productName, String start, String end){
		return this.backend.generateReport(productName, start, end);
	}
	
	//save all product data when admin log out
		public void storeProductInfoByAdmin (){
			String electronicsData = "";
			String fashionData = "";
			String homeandfurnitureData = "";
			String sportsandbooksData = "";
			for(Product product : this.backend.getProductList()){
				switch(product.getType()){
				case ELECTRONICS:
					Electronics e = (Electronics) product;
					electronicsData += e.toString() + "\r\n";break;
				case FASHION:
					Fashion f = (Fashion) product;
					fashionData += f.toString() + "\r\n";break;
				case HOMEANDFURNITURE:
					Homeandfurniture h = (Homeandfurniture) product;
					homeandfurnitureData += h.toString() + "\r\n";break;
				case SPORTSANDBOOKS:
					Sportsandbooks s = (Sportsandbooks) product;
					sportsandbooksData += s.toString() + "\r\n";break;
				default:break;
				}
			};
			FileHandler.writeToFile(electronicsData, FileHandler.ELECTRONICS_FILE);
			FileHandler.writeToFile(fashionData, FileHandler.FASHION_FILE);
			FileHandler.writeToFile(homeandfurnitureData, FileHandler.HOMEANDFURNITURE_FILE);
			FileHandler.writeToFile(sportsandbooksData, FileHandler.SPORTSANDBOOKS_FILE);
		}
}

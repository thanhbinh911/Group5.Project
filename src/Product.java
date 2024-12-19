
import java.util.HashMap;
import javax.swing.ImageIcon;

public class Product {

    private HashMap<String, Object> props = new HashMap<String, Object>();
	private HashMap<String, String> dispNames = new HashMap<String, String>();
	
	private String name;
    private String description;

	private ImageIcon image = null;
	
	private double price;
	private int quantity;
	private ProductType type;

    public Product(String name, double price, int quantity, ProductType type, String description){
		setName(name);
		setPrice(price);
		setType(type);
		setQuantity(quantity);
        setDescription(description);
		this.setProperty("price", "Price ($)", price);
	}

    public Product(String name, double price, int quantity, String description){
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		setDescription(description);
		this.setProperty("price", "Price ($)", price);
	}

    public void setImage(String url){
		this.image = ShopController.generateIcon(url, 150, 150);
	}

    public void setName(String name){
		this.name = name;
	}

    public String getName(){
		return name;
	}

    public void setProperty(String id, String displayName, Object value){
		this.props.put(id, value);
		this.dispNames.put(id, displayName);
	}

    public Object getPropertyValue(String id){
		return props.get(id);
	}

    public String getPropertyDisplayName(String id){
		return dispNames.get(id);
	}

    public String getPropertiesAsText(){
		String out = "<html>";
		for(String key : this.props.keySet()) out += (getPropertyDisplayName(key) + ": " + getPropertyValue(key).toString()) + "<br/>";
		out += "</html>";
		return out;
	}

    public boolean hasProperty(String id){
		return props.containsKey(id);
	}

    public ImageIcon getImage(){
		switch (this.type) {
		case ELECTRONICS:
			return ShopController.ELECTRONICS_ICON;
		case FASHION:
			return ShopController.FASHION_ICON;
		case SPORTSANDBOOKS:
			return ShopController.SPORTSANDBOOKS_ICON;
		case HOMEANDFURNITURE:
			return ShopController.HOMEANDFURNITURE_ICON;
		}
		if(this.image == null) return ShopController.LOGO_ICON;
		else return this.image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}




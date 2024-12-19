
public class Sportsandbooks extends Product{
    private String description;

    public Sportsandbooks(String name, double price, int quantity, ProductType type, String description){
        super(name, price, quantity, type, description);
    }

    public Sportsandbooks(ProductType type, String name, double price, String description, int quantity){
        super(name, price, quantity, type, description);
        this.description = description;
        this.setProperty("description", "Description", description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
	public String toString() {
		return this.getName() + FileHandler.SPLIT_COMMA + 
				this.getPrice()	+ FileHandler.SPLIT_COMMA + 
				description + FileHandler.SPLIT_COMMA + 
				this.getQuantity();
	}
}

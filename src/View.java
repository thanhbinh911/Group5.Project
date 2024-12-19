
import javax.swing.JPanel;

public abstract class View extends JPanel {
	private static final long serialVersionUID = 1L;
    
	private ShopController controller;

    public void setController(ShopController c){
		this.controller = c;
	}

    public ShopController getController(){
		return controller;
	}

    public abstract void initialize();

}


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminProductDetails extends JDialog {

	protected static ProductType dialogType;

	protected final JPanel contentPanel;

	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;
	private JLabel descriptionLabel;

	private JTextField productName;
	private JSpinner price;
	private JSpinner quantity;
	private JTextField description;

	public static void display(ShopController c, AdminProductDetails dialog) {
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}
	
	public static void setProductType(ProductType type){
		dialogType = type;
	}

	public static void displayElectronicsDetails(DefaultTableModel tm, ShopController c, ProductType type) {
		AdminElectronicsDetails dialog = new AdminElectronicsDetails(c,tm);
		setProductType(type);
		display(c, dialog);
	}

	public static void displaySportsandbooksDetails(DefaultTableModel dt, ShopController c, ProductType type) {
		AdminSportsandbooksDetails dialog = new AdminSportsandbooksDetails(c, dt);
		setProductType(type);
		display(c, dialog);
	}
	
	public static void displayHomeandfurnitureDetails(DefaultTableModel dt, ShopController c, ProductType type) {
		AdminHomeandfurnitureDetails dialog = new AdminHomeandfurnitureDetails(c, dt);
		setProductType(type);
		display(c, dialog);
	}

	public static void displayFashionDetails(DefaultTableModel dt, ShopController c, ProductType type) {
		AdminFashionDetails dialog = new AdminFashionDetails(c, dt);
		setProductType(type);
		display(c, dialog);
	}

	public Product toProduct() {
		String name = this.getProductName().getText();
		double price =  (double) this.getPrice().getValue();
		int quantity = (int) this.getQuantity().getValue();
		String description = (String) this.getDescription().getText();
		return new Product(name, price, quantity, description);
	}
	

	public boolean validateInput(){
		// Text field validate
		if(Utility.isEmpty(this.getProductName().getText())){
			return false;
		}
		if(Utility.isEmpty(this.getPrice().getValue())){
			return false;
		}
		if(Utility.isEmpty(quantity)){
			return false;
		}
		if(Utility.isEmpty(this.getDescription().getText())){
			return false;
		}
		return true;
	}

	public AdminProductDetails(final ShopController c, final DefaultTableModel dt) {

		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		dialogType = ProductType.ELECTRONICS;
		this.getContentPane().setLayout(new BorderLayout());
		this.nameLabel = new JLabel("Name");
		this.priceLabel = new JLabel("Price");
		this.quantityLabel = new JLabel("Quantity");
		this.descriptionLabel = new JLabel("Description");

		this.productName = new JTextField();
		this.price = new JSpinner();
		price.setModel(new SpinnerNumberModel(100.0, 0.0, 10000.0, 1.0));
		this.quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		this.description = new JTextField();

		this.contentPanel.add(nameLabel);
		this.contentPanel.add(productName);
		this.contentPanel.add(priceLabel);
		this.contentPanel.add(price);
		this.contentPanel.add(quantityLabel);
		this.contentPanel.add(quantity);
		this.contentPanel.add(descriptionLabel);
		this.contentPanel.add(description);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JDialog me = this;
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validateInput()){
							Product p = toProduct();
							c.addProduct(p);
							dt.addRow(new Object[] { p.getType(), p.getName(), p.getPrice(), p.getQuantity() });
							me.dispose();
						}else{
							c.showPopup("Wrong input! \nYour input can't be null.  ");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getProductName() {
		return productName;
	}

	public void setProductName(JTextField productName) {
		this.productName = productName;
	}

	public JSpinner getPrice() {
		return price;
	}

	public JSpinner getQuantity() {
		return quantity;
	}

	public JTextField getDescription() {
		return description;
	}

}

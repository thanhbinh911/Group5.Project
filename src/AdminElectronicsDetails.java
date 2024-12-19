
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

public class AdminElectronicsDetails extends AdminProductDetails {
	private JLabel descriptionLabel;
	private JTextField description;

	public AdminElectronicsDetails(ShopController c, DefaultTableModel tm) {
		super(c,tm);
		setTitle("Create a Electronics product");
		descriptionLabel = new JLabel("Description");
		description = new JTextField();

		contentPanel.add(descriptionLabel);
		contentPanel.add(description);

		contentPanel.setLayout(new GridLayout(6, 2));
		this.getContentPane().add(contentPanel, BorderLayout.NORTH);
	}

	public JTextField getDescription() {
		return description;
	}

	@Override
	public Product toProduct() {
		Product p = super.toProduct();
		String description = this.getDescription().getText();
		return new Electronics(ProductType.ELECTRONICS, p.getName(), p.getPrice(), description, p.getQuantity());
	}
	
	@Override
	public boolean validateInput(){
		if(super.validateInput()){
			String description = getDescription().getText();
			if(Utility.isEmpty(description)){
				return false;
			}
			return true;
		}
		return false;
	}
}

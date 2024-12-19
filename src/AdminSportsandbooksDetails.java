import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;


public class AdminSportsandbooksDetails extends AdminProductDetails{

		private JLabel descriptionLabel;
		
		private JTextField descriptionTxtField;

		public AdminSportsandbooksDetails(ShopController c,DefaultTableModel tm) {
			super(c,tm);
			setTitle("Create a Sports & Books product");
			descriptionLabel = new JLabel("Description:");
			descriptionTxtField = new JTextField();

			contentPanel.add(descriptionLabel);
			contentPanel.add(descriptionTxtField);
			contentPanel.setLayout(new GridLayout(7, 2));
			
			this.getContentPane().add(contentPanel, BorderLayout.NORTH);
		}
		
		@Override
		public Product toProduct() {
			Product p = super.toProduct();
			String description = this.getDescription().getText();
			return new Sportsandbooks(ProductType.FASHION, p.getName(),p.getPrice(), description, p.getQuantity());
		}

		public JTextField getDescription() {
			return descriptionTxtField;
		}
		
		@Override
		public boolean validateInput(){
			if(super.validateInput()){
				String descriptionTxtField = getDescription().getText();
				if(Utility.isEmpty(descriptionTxtField)){
					return false;
				}
				return true;
			}
			return false;
		}

}

package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.LimitLoginException;
import main.Shop;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class ProductView extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField productName;
	private JTextField productStock;
	private JTextField productPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			Shop shop = new Shop();
			int option = 0;

			ProductView dialog = new ProductView(shop, option);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param option
	 * @param shop
	 */
	public ProductView(Shop shop, int option) {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 228, 434, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				Product product = null;
				int stock = 0;

				switch (option) {
				case 2:
					name = productName.getText();
					product = shop.findProduct(name);
					if (product == null) {
						double wholesalerPrice = Double.parseDouble(productPrice.getText());
						stock = Integer.parseInt(productStock.getText());
						shop.inventory.add(new Product(name, wholesalerPrice, true, stock));
						JOptionPane.showMessageDialog(null, "Producto añadido correctamente", "Información",
								JOptionPane.INFORMATION_MESSAGE);

						dispose();

					} else {
						JOptionPane.showMessageDialog(ProductView.this,
								"El producto con nombre " + name + " ya existe en el inventario", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					break;

				case 3:
					name = productName.getText();
					product = shop.findProduct(name);

					if (product != null) {

						stock = Integer.parseInt(productStock.getText());
						product.setStock(product.getStock() + stock);
						JOptionPane.showMessageDialog(null,
								"El stock del producto " + name + " ha sido actualizado a " + product.getStock(),
								"Información", JOptionPane.INFORMATION_MESSAGE);

						dispose();

					} else {
						JOptionPane.showMessageDialog(ProductView.this,
								"No se ha encontrado el producto con nombre " + name, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					break;

				case 9:
					name = productName.getText();
					product = shop.findProduct(name);

					if (product != null) {
						shop.inventory.remove(product);
						JOptionPane.showMessageDialog(null, "El producto con nombre " + name + " se ha eliminado",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						dispose();
					} else {
						JOptionPane.showMessageDialog(ProductView.this,
								"No se ha encontrado el producto con nombre " + name, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
			}
		});
		okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		JLabel productNameLabel = new JLabel("Nombre producto:");
		productNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productNameLabel.setBounds(68, 62, 124, 14);
		getContentPane().add(productNameLabel);

		productName = new JTextField();
		productName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productName.setBounds(186, 60, 175, 20);
		getContentPane().add(productName);
		productName.setColumns(10);

		JLabel productStockLabel = new JLabel("Stock producto:");
		productStockLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productStockLabel.setBounds(68, 112, 108, 14);
		getContentPane().add(productStockLabel);

		productStock = new JTextField();
		productStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productStock.setColumns(10);
		productStock.setBounds(186, 109, 175, 20);
		getContentPane().add(productStock);

		JLabel productPriceLabel = new JLabel("Precio producto:");
		productPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productPriceLabel.setBounds(68, 162, 108, 14);
		getContentPane().add(productPriceLabel);

		productPrice = new JTextField();
		productPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		productPrice.setColumns(10);
		productPrice.setBounds(186, 160, 175, 20);
		getContentPane().add(productPrice);

		switch (option) {
		case 2:
			setTitle("Añadir producto");
			break;

		case 3:
			setTitle("Añadir stock");
			productPriceLabel.setVisible(false);
			productPrice.setVisible(false);
			break;

		case 9:
			setTitle("Eliminar producto");
			productPriceLabel.setVisible(false);
			productPrice.setVisible(false);
			productStockLabel.setVisible(false);
			productStock.setVisible(false);
			break;
		}

		// Se vuelve a menú principal cerrando ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				dispose();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}

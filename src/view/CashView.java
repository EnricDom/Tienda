package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

import main.Shop;
import java.awt.Color;
import javax.swing.SwingConstants;

public class CashView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Shop shop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Shop shop = new Shop();

			CashView dialog = new CashView(shop);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CashView(Shop shop) {
		this.shop = shop;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setTitle("Caja");

		JLabel lblNewLabel = new JLabel("Dinero en caja: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(48, 109, 116, 28);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setText(getName());
		textField.setBounds(152, 148, 102, 28);
		getContentPane().add(textField);

		textField.setText(shop.cash.getValue() + " " + shop.cash.getCurrency());

		// Se vuelve a menú principal cerrando ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				dispose();
			}
		});
	}

}

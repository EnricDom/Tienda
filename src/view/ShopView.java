package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ShopView extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Shop shop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopView frame = new ShopView();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopView() {
		this.addKeyListener(this);

		shop = new Shop();
		shop.loadInventory();
		
		setTitle("MiTienda.com - Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione o pulse una opción:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(39, 29, 262, 20);
		contentPane.add(lblNewLabel);
		
		//OPCION o BOTON 1
		JButton btnNewButton = new JButton("1. Contar caja");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	openCashView(shop);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(57, 60, 262, 36);
		contentPane.add(btnNewButton);
		btnNewButton.addKeyListener(this);
		
		//OPCION o BOTON 2
		JButton btnNewButton_1 = new JButton("2. Añadir producto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	openProductView(shop,2);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBounds(57, 107, 262, 36);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addKeyListener(this);
		
		//OPCION o BOTON 3
		JButton btnNewButton_2 = new JButton("3. Añadir stock");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	openProductView(shop,3);
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setBounds(57, 154, 262, 36);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addKeyListener(this);
		
		//OPCION o BOTON 9
		JButton btnNewButton_3 = new JButton("9. Eliminar producto");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	openProductView(shop,9);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setBounds(57, 201, 262, 36);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();
        
        switch (key) {
            case '1':
            	openCashView(shop);
                break;
                
            case '2':
            	openProductView(shop,2);
                break;
                
            case '3':
            	openProductView(shop,3);
                break;
                
            case '9':
            	openProductView(shop,9);
                break;
                
            default:
                // No hacer nada si se presiona una tecla no asociada a una opción del menú
                break;
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void openCashView(Shop shop) {
		CashView cashView = new CashView(shop);
		cashView.setVisible(true);
		this.setVisible(false);
	}
	
	public void openProductView(Shop shop, int option) {
		ProductView productView = new ProductView(shop, option);
		productView.setVisible(true);
		this.setVisible(false);
	}

}

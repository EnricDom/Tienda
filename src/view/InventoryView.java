package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.Shop;
import model.Product;

import javax.swing.JList;
import javax.swing.JTable;

public class InventoryView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            Shop shop = new Shop();
            
			InventoryView dialog = new InventoryView(shop);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param shop 
	 */
	public InventoryView(Shop shop) {
		setTitle("Inventario");
		setBounds(100, 100, 598, 379);
        getContentPane().setLayout(new BorderLayout());

        // Crear una matriz de objetos para los datos de la tabla
        Object[][] data = new Object[shop.inventory.size()][5];
        for (int i = 0; i < shop.inventory.size(); i++) {
            Product product = shop.inventory.get(i);
            data[i][0] = product.getName();
            data[i][1] = product.getPublicPrice();
            data[i][2] = product.getWholesalerPrice();
            data[i][3] = product.isAvailable();
            data[i][4] = product.getStock();
        }

        // Crear un modelo de tabla
        String[] columnNames = {"Nombre", "Precio Público", "Precio Mayorista", "Disponible", "Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        // Crear la tabla con el modelo de tabla
        table = new JTable(tableModel);

        // Agregar la tabla a un JScrollPane y agregarlo al contenido del JDialog
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Se vuelve a menú principal cerrando ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
	}
}

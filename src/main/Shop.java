package main;

import model.Amount;
import model.Employee;
import model.Product;
import model.Sale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop {

	private Amount cash;
	private ArrayList<Product> inventory;
	private ArrayList<Sale> sales;
	private Employee employee;

	final static double TAX_RATE = 1.04;
	final static int num = 10;

	private Shop() {
		inventory = new ArrayList<Product>();
		sales = new ArrayList<Sale>();
		cash = new Amount(0.0);
		employee = new Employee("Paco",1);
	}

	public static void main(String[] args) {

		Shop shop = new Shop();
		shop.loadInventory();
		shop.initSession();
		
		Scanner scanner = new Scanner(System.in);
			
		int opcion = 0;
		boolean exit = false;

		do {
			System.out.println("\n");
			System.out.println("===========================");
			System.out.println("Menu principal miTienda.com");
			System.out.println("===========================");
			System.out.println("1) Contar caja");
			System.out.println("2) Añadir producto");
			System.out.println("3) Añadir stock");
			System.out.println("4) Marcar producto proxima caducidad");
			System.out.println("5) Ver inventario");
			System.out.println("6) Venta");
			System.out.println("7) Ver ventas");
			System.out.println("8) Ver valor total ventas");
			System.out.println("9) Eliminar producto del Inventario");
			System.out.println("10) Salir programa");
			System.out.println("11) Ver ventas de un cliente");
			System.out.println("\n");

			System.out.print("Seleccione una opción: ");
			try {
				opcion = scanner.nextInt();
			} catch (InputMismatchException ex) {
				scanner.next();
			}
			System.out.println("\n");

			switch (opcion) {
			case 1:
				shop.showCash();
				break;

			case 2:
				shop.addProduct();
				break;

			case 3:
				shop.addStock();
				break;

			case 4:
				shop.setExpired();
				break;

			case 5:
				shop.showInventory();
				break;

			case 6:
				shop.sale();
				break;

			case 7:
				shop.showSales();
				break;

			case 8:
				shop.showValueSales();
				break;

			case 9:
				shop.deleteProduct();
				break;

			case 10:
				System.out.println("Se ha cerrado correctamente");
				exit = true;
				break;

			case 11:
				shop.showClientSales();
				break;

			default:
				System.out.println("Elige una opcion del menu correcta");
				break;
			}
		} while (!exit);
	}
	
	/**
	 * initSession
	 */

	private void initSession() {
		boolean logged = false;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print("Introduzca numero de empleado: ");
			int user = scanner.nextInt();
			System.out.print("Introduzca contrasenya: ");
			String passw = scanner.next();
			logged = employee.login(user, passw);
		} while (!logged);
	}
	
	/**
	 * load initial inventory to shop
	 */
	private void loadInventory() {

		try {
			File archivo = new File("/Users/manana/eclipse-workspace/dam2_m03_uf2_poo_shop2/Files/inputInventory.txt");
			if (archivo.canRead()) {
				FileReader fr = null;
				fr = new FileReader(archivo);
				BufferedReader br = new BufferedReader(fr);
				String linea = br.readLine();

				while (linea != null) {
					String[] parts = linea.split(";");
					String[] productName = parts[0].split(":");
					String[] productPrice = parts[1].split(":");
					String[] productStock = parts[2].split(":");
					inventory.add(new Product(productName[1], Double.parseDouble(productPrice[1]), true,
							Integer.parseInt(productStock[1])));

					linea = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * show current total cash
	 */
	private void showCash() {
		System.out
				.println("Dinero actual: " + cash.getValue() + " " + cash.getCurrency());
	}

	/**
	 * add a new product to inventory getting data from console
	 */
	private void addProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nombre: ");
		String name = scanner.nextLine();
		Product product = findProduct(name);
		if (product == null) {
			System.out.print("Precio mayorista: ");
			double wholesalerPrice = scanner.nextDouble();
			System.out.print("Stock: ");
			int stock = scanner.nextInt();
			inventory.add(new Product(name, wholesalerPrice, true, stock));
			System.out.println("Producto añadido correctamente");
		} else {
			System.out.println("El producto con nombre " + name + " ya existe en el inventario");
		}
	}

	/**
	 * delete a product to inventory getting data from console
	 */
	private void deleteProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto a eliminar: ");
		String name = scanner.next();
		Product product = findProduct(name);

		if (product != null) {
			inventory.remove(product);
			System.out.println("El producto con nombre " + name + " se ha eliminado");
		} else {
			System.out.println("No se ha encontrado el producto con nombre " + name);
		}
	}

	/**
	 * add stock for a specific product
	 */
	private void addStock() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();
		Product product = findProduct(name);

		if (product != null) {
			// ask for stock
			System.out.print("Seleccione la cantidad a añadir: ");
			int stock = scanner.nextInt();
			// update stock product
			product.setStock(product.getStock() + stock);
			System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

		} else {
			System.out.println("No se ha encontrado el producto con nombre " + name);
		}
	}

	/**
	 * set a product as expired
	 */
	private void setExpired() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();

		Product product = findProduct(name);

		if (product != null) {
			product.expire();
			System.out.println("El precio del producto " + name + " ha sido actualizado a " + product.getPublicPrice());

		}
	}

	/**
	 * show all inventory
	 */
	private void showInventory() {
		System.out.println("Contenido actual de la tienda:");
		for (Product product : inventory) {
			if (product != null) {
				System.out.println(product.toString());
			}
		}
	}

	/**
	 * make a sale of products to a client
	 */
	private void sale() {

		// ask for client name
		Scanner sc = new Scanner(System.in);
		System.out.print("Realizar venta, escribir nombre cliente: ");
		String client = sc.nextLine();

		// sale product until input name is not 0
		double totalAmount = 0.0;
		ArrayList<Product> venta = new ArrayList<Product>();
		Product product;
		String name = "";
		while (!name.equals("0")) {
			System.out.print("Introduce el nombre del producto, escribir 0 para terminar:");
			name = sc.nextLine();

			if (name.equals("0")) {
				break;
			}
			product = findProduct(name);
			boolean productAvailable = false;

			if (product != null && product.isAvailable()) {
				productAvailable = true;
				totalAmount += product.getPublicPrice();
				product.setStock(product.getStock() - 1);
				// if no more stock, set as not available to sale
				if (product.getStock() == 0) {
					product.setAvailable(false);
				}
				venta.add(product);
				System.out.println("Producto añadido con éxito");
			}

			if (!productAvailable) {
				System.out.println("Producto no encontrado o sin stock");
			}
		}
		// Obtenemos y guardamos la fecha del sistema
		Date myDate = new Date();

		// Aquí obtenemos el formato que deseamos
		String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(myDate);

		// show cost total
		totalAmount = totalAmount * TAX_RATE;
		cash.setValue(totalAmount + cash.getValue());
		System.out.println("Venta realizada con éxito, total: " + Math.round(totalAmount * 100.00) / 100.00 + " "
				+ cash.getCurrency() + " Date: " + formatDate);

		// guardamos venta
		sales.add(new Sale(client, venta, myDate, totalAmount));
	}

	/**
	 * show all sales
	 */
	private void showSales() {

		if (sales.isEmpty()) {
			System.out.println("Lista de ventas vacia");
		} else {
			System.out.println("Lista de ventas:");
			for (Sale sale : sales) {
				if (sale != null) {
					System.out.println(sale.toString());
				}
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			System.out.print("Desea guardar las ventas realizadas? escribir 0 para guardar: ");
			String op = sc.nextLine();

			if (op.equals("0")) {
				// Obtenemos y guardamos la fecha del sistema
				Date myDate = new Date();

				// Aquí obtenemos el formato que deseamos
				String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
				
				try (FileWriter path = new FileWriter("/Users/manana/eclipse-workspace/dam2_m03_uf2_poo_shop2/Files/sales_"+formatDate+".txt"))
		        {
		            PrintWriter file = new PrintWriter(path);
		            
		            for (Sale sale : sales) {
						if (sale != null) {
							formatDate= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(sale.getDate());
							ArrayList<Product> productList = new ArrayList<Product>();
							productList = sale.getProducts();
							String list = "";
							
							for (Product product : productList) {
								if(product!=null) {
									list=list+product.toList();
								}
							}
							
							file.println((sales.indexOf(sale)+1)+";Cliente="+sale.getClient()+";Date="+formatDate+";");
							file.println((sales.indexOf(sale)+1)+";Products="+list);
							file.println((sales.indexOf(sale)+1)+";Amount="+sale.getAmount()+";");
						}
					}	
					System.out.println("");
		            System.out.println("Ventas guardadas correctamente");

		        } catch (Exception e) {
		            e.printStackTrace();
		        } 
			}
		}
	}

	/**
	 * show all client sales
	 */
	private void showClientSales() {

		// ask for client name
		Scanner sc = new Scanner(System.in);
		System.out.print("Buscar ventas, escribir nombre cliente: ");
		String client = sc.nextLine();
		boolean encontrado = false;

		while (true) {
			if (sales.isEmpty()) {
				System.out.println("Lista de ventas vacia");
				break;
			} else {

				for (Sale sale : sales) {
					if (sale != null) {
						if (sale.getClient().equalsIgnoreCase(client)) {
							encontrado = true;
							break;
						}
					}
				}
				if (encontrado == true) {
					if (sales.isEmpty()) {
						System.out.println("El cliente no existe");
						break;
					} else {
						System.out.println("Lista de ventas:");
						for (Sale sale : sales) {
							if (sale != null) {
								if (sale.getClient().equalsIgnoreCase(client)) {
									System.out.println(sale);
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	/**
	 * show all value sales
	 */
	private void showValueSales() {

		if (sales.isEmpty()) {
			System.out.println("Lista de ventas vacia");
		} else {
			double res = 0;
			System.out.print("Valor de todas las ventas: ");
			for (Sale sale : sales) {
				if (sale != null) {
					res += sale.getAmount();
				}
			}
			System.out.println(Math.round(res * 100.00) / 100.00 + " " + cash.getCurrency());
		}
	}

	/**
	 * find product by name
	 * 
	 * @param product name
	 */
	private Product findProduct(String name) {

		for (Product product : inventory) {
			if (product.getName().equalsIgnoreCase(name)) {
				return product;
			}
		}

		return null;

	}

}
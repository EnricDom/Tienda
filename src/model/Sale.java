package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sale {
	private String client;
	private ArrayList<Product> products;
	private Date date;
	private Amount amount;
	
	
	public Sale(String client, ArrayList<Product> products, Date date, double amount) {
		super();
		this.client = client;
		this.products = products;
		this.date=date;
		this.amount = new Amount(amount);
	}
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public double getAmount() {
		return amount.getValue();
	}
	public void setAmount(double amount) {
		this.amount.setValue(amount);
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return this.date;
	}
	@Override
	public String toString() {
		String lista = "";
		for (Product product : products) {
			if (product!=null) {
				lista=lista + product.toString();
			}
		}
		String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(this.getDate());
		return "Sale [client = " + client.toUpperCase() 
		+ ", products =" + lista 
		+ ", amount = " + this.amount.getValue() + " "+ this.amount.getCurrency() + ", date = " + formatDate +"]";
	}
	
	
	
	

}

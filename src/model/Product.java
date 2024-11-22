package model;

public class Product {
	private String name;
	private Amount publicPrice;
	private Amount wholesalerPrice;
	private boolean available;
	private int stock;

	static double EXPIRATION_RATE = 0.60;

	public Product(String name, double wholesalerPrice, boolean available, int stock) {
		super();
		this.name = name;
		this.wholesalerPrice = new Amount(wholesalerPrice);
		this.available = available;
		this.stock = stock;
		this.publicPrice = new Amount(2 * wholesalerPrice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPublicPrice() {
		return publicPrice.getValue();
	}

	public void setPublicPrice(double publicPrice) {
		this.publicPrice.setValue(publicPrice);
	}

	public double getWholesalerPrice() {
		return wholesalerPrice.getValue();
	}

	public void setWholesalerPrice(double wholesalerPrice) {
		this.wholesalerPrice.setValue(wholesalerPrice);
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void expire() {
		this.publicPrice.setValue(this.getPublicPrice() * EXPIRATION_RATE);
	}

	@Override
	public String toString() {
		return " " + this.name + ", publicPrice = " + this.publicPrice.toString() + ", wholesalerPrice = "
				+ this.wholesalerPrice.toString() + ", stock = " + this.stock;
	}

	public String toList() {
		return this.name + "," + wholesalerPrice.toList() + ";";
	}

}

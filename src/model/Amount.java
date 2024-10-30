package model;

public class Amount {
	private double value;
	private final String currency = "€";

	public Amount(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = Math.round(value * 100.00) / 100.00;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		return this.value + " " + this.currency;
	}

	public String toList() {
		return this.value + this.currency;
	}

}

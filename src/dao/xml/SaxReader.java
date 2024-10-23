package dao.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Product;

public class SaxReader extends DefaultHandler {
	
	private ArrayList<Product> products;
	private Product product;
	private String value;
	private String parsedElement;

	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	@Override
	public void startDocument() throws SAXException {
		this.products = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "product":
			this.product = new Product(attributes.getValue("name") != null ? attributes.getValue("name") : "empty", 0, true, 0);
			break;
		case "wholesalerPrice":
			break;
		case "stock":
			break;
		}
		this.parsedElement = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch, start, length);
		switch (parsedElement) {
		case "product":
			break;
		case "wholesalerPrice":
			this.product.setPublicPrice(Float.valueOf(value));
			break;
		case "stock":
			this.product.setStock(Integer.valueOf(value));
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// we add the product into the arrayList
		if (qName.equals("product"))
			this.products.add(product);
		this.parsedElement = "";
	}
}

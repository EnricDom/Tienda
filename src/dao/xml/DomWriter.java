package dao.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Product;

public class DomWriter {
	

	private Document document;
	
	public DomWriter() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			System.out.println("ERROR generating document");
		}
	}	

	public boolean generateDocument(ArrayList<Product> inventory) {
		
		boolean generated = false;
		
		//PARENT NODE
		//root node
		Element products = document.createElement("products");
		products.setAttribute("total", "" + inventory.size());
		document.appendChild(products);
		
		
		for(int i = 0; i<inventory.size(); i++) {
			//CHILD NODES
			//child node into root node "products"
			Element product = document.createElement("product");
			product.setAttribute("id", "" + (i+1));
			products.appendChild(product);
			
			//FINAL NODES
			//child into product with attribute and content
			Element name = document.createElement("name");
			name.setTextContent(inventory.get(i).getName());
			product.appendChild(name);
			
			//child into product with 2 attributes and content
			Element price = document.createElement("price");
			price.setAttribute("currency", "€");
			price.setTextContent("" + inventory.get(i).getWholesalerPrice());
			product.appendChild(price);
			
			//child into product with 2 attributes and content
			Element stock = document.createElement("stock");
			stock.setTextContent("" + inventory.get(i).getStock());
			product.appendChild(stock);
			
		}
		
		generated = generateXml();
		return generated;
	}
	
	private boolean generateXml() {
		boolean generated = false;
		try {			
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			
			Source source = new DOMSource(document);
			
			// Obtenemos y guardamos la fecha del sistema
			Date myDate = new Date();

			// Aquí obtenemos el formato que deseamos
			String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
			
			File file = new File("Files" + File.separator + "inventory_" + formatDate + ".xml");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			Result result = new StreamResult(pw);
			
			transformer.transform(source, result);
			generated = true;
		} catch (IOException e) {
			System.out.println("Error when creating writter file");
			generated = false;
		} catch (TransformerException e) {
			System.out.println("Error transforming the document");
			generated = false;
		}
		return generated;
	}
}

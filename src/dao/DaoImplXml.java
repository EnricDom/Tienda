package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import exception.EmployeeNotFoundException;
import exception.InvalidPasswordException;
import model.Employee;
import model.Product;
import dao.xml.SaxReader;
import dao.xml.DomWriter;

public class DaoImplXml implements Dao {

	public ArrayList<Product> inventory;

	@Override
	public void connect() {
		// TODO Auto-generated method stub
	}

	@Override
	public Employee getEmployee(int employeeId, String password)
			throws EmployeeNotFoundException, InvalidPasswordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
	}

	@Override
	public ArrayList<Product> getInventory() {
		inventory = new ArrayList<Product>();

		// Read an existing xml document
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;

		try {
			parser = factory.newSAXParser();
			File file = new File("Files" + File.separator + "inventory.xml");
			SaxReader saxReader = new SaxReader();
			parser.parse(file, saxReader);
			inventory = saxReader.getProducts();
			return inventory;

		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
			return null;
		} catch (IOException e) {
			System.out.println("ERROR file not found");
			return null;
		}
	}

	@Override
	public boolean writeInventory(ArrayList<Product> lista) {
		boolean generated = false;

		// Create a new xml document
		DomWriter domWriter = new DomWriter();
		generated = domWriter.generateDocument(inventory);
		return generated;
	}

}

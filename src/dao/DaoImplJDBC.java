package dao;

import model.Employee;
import model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.EmployeeNotFoundException;
import exception.InvalidPasswordException;

public class DaoImplJDBC implements Dao {

	private Connection connection;

	@Override
	public void connect() {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "root";
		String pass = "";
		try {
			this.connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Employee getEmployee(int employeeId, String password)
			throws EmployeeNotFoundException, InvalidPasswordException {
		Employee employee = null;
		// Prepare query
		String query = "SELECT * FROM employee WHERE employeeID = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			// Set id to search for
			ps.setInt(1, employeeId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					// Employee found, now verify the password
					String storedPassword = rs.getString("password");
					if (storedPassword.equals(password)) {
						// Password matches, create Employee object
						employee = new Employee(rs.getString("name"), rs.getInt("employeeId"), storedPassword);
					} else {
						// Password does not match, throw exception
						throw new InvalidPasswordException("Password does not match for employee ID: " + employeeId);
					}
				} else {
					// No employee found, throw exception
					throw new EmployeeNotFoundException("No employee found with ID: " + employeeId);
				}
			}
		} catch (SQLException e) {
			// In case of an SQL error
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Failed to close the database connection.");
			}
		}

	}

	@Override
	public ArrayList<Product> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> lista) {
		// TODO Auto-generated method stub
		return false;
	}

}

package model;


import java.sql.SQLException;

import dao.Dao;
import dao.DaoImplJDBC;
import main.Logable;

public class Employee extends Person implements Logable{
	

	public int employeeId;
	public Dao dao;
	public String password;
	
	public Employee(String name, int id, String password) throws SQLException {
		super(name);
		this.employeeId = id;
		this.password = password;
		this.dao = new DaoImplJDBC();
		this.dao.connect();
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	

	@Override
	public boolean login(int id_user, String password) {
		boolean logged = false;
		
		if (id_user == this.employeeId && password.equalsIgnoreCase(this.password)) {
			System.out.println("Login correcto");
			logged = true;
		} 		
		return logged;
	}
}

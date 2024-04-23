package model;

import main.Logable;

public class Employee extends Person implements Logable{
	

	int employeeId;
	final static int USER = 123;
	final static String PASSWORD = "test";
	
	public Employee(String name) {
		super(name);
		this.employeeId = USER;
	}

	protected int getEmployeeId() {
		return employeeId;
	}

	protected void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public boolean login(int user, String password) {
		boolean logged = false;
		if (user == USER && password.equalsIgnoreCase(PASSWORD)) {
			System.out.println("Login correcto");
			logged = true;
		} else {
			System.out.println("Login incorrecto");
		}
		return logged;
	}
	

	
	
}

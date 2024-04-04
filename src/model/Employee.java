package model;

public class Employee extends Person {
	

	int employeeId;
	final int USER = 123;
	final String PASSWORD = "test";

	
	public Employee(String name, int employeeId) {
		super(name);
		this.employeeId = employeeId;
	}

	protected int getEmployeeId() {
		return employeeId;
	}

	protected void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	/**
	 * login 
	 */

	public boolean login(int u, String p) {
		boolean logged = false;
		if (u == USER&&p.equalsIgnoreCase(PASSWORD)) {
			System.out.println("Login correcto");
			logged = true;
		} else {
			System.out.println("Login incorrecto");
		}
		return logged;
	}
	

}

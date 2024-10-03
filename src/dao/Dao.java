package dao;

import java.util.ArrayList;

import exception.EmployeeNotFoundException;
import exception.InvalidPasswordException;
import model.Employee;
import model.Product;

public interface Dao {
	
    public void connect() ;
    
    public Employee getEmployee(int employeeId, String password) throws EmployeeNotFoundException, InvalidPasswordException;
    
    public void disconnect();
    
    public ArrayList<Product> getInventory();
    
    public boolean writeInventory(ArrayList<Product> lista);

}

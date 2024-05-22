package dao;

import java.sql.SQLException;

import exception.EmployeeNotFoundException;
import exception.InvalidPasswordException;
import model.Employee;

public interface Dao {
	
    void connect() throws SQLException;
    
    Employee getEmployee(int employeeId, String password) throws EmployeeNotFoundException, InvalidPasswordException;
    
    void disconnect();

}

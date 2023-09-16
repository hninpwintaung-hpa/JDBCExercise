package com.employee.service;

import java.sql.SQLException;
import java.util.List;

import com.employee.dao.EmployeeDAO;
import com.employee.dao.EmployeeDAOImpl;
import com.employee.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	public int[] addEmployeeBatch(List<Employee> employees) throws SQLException {
		return employeeDAO.addEmployeeBatch(employees);
	}

	public void insertEmplyoee(Employee employee) throws SQLException {
		employeeDAO.addEmployee(employee);
	}

	public void updateEmployee(int empId, Employee employee) throws SQLException {
		employeeDAO.updateEmployee(empId, employee);
	}

	public List<Employee> getAllEmployee() throws SQLException {
		return employeeDAO.findAllEmployee();
	}

	public List<Employee> getEmployeesByLastName(String lastName) throws SQLException {
		return employeeDAO.getEmployeesByLastName(lastName);
	}

	public List<Employee> getEmployeesByFirstName(String firstName) throws SQLException {
		return employeeDAO.getEmployeesByFirstName(firstName);
	}

	public List<Employee> getAllEmployeeByAscHireDate() throws SQLException {
		return employeeDAO.getAllEmployeeByAscHireDate();
	}

	public List<Employee> getAllEmployeeByDescHireDate() throws SQLException {
		return employeeDAO.getAllEmployeeByDescHireDate();
	}

	public void deleteEmployee(int empId) throws SQLException {
		employeeDAO.deleteEmployee(empId);
	}

	public Employee getEmployeeById(int id) throws SQLException {
		return employeeDAO.getEmployeeById(id);
	}

	public int getNumberOfTotalEmployee() throws SQLException{
		return employeeDAO.getNumberOfTotalEmployee();
	}
	
	public String getAllEmployeeAvgHireDate() throws SQLException{
		return employeeDAO.getAllEmployeeAvgHireDate();
	}
	public void updateEmployeesSalaryByBatch(Object[][] employeesToUpdate) throws SQLException{
		 employeeDAO.updateEmployeesSalaryByBatch(employeesToUpdate);
	}
	public Boolean employeeBackup()throws SQLException{
		return employeeDAO.employeeBackup();
	}
	public Boolean restoreEmployee(String backupFilePath) throws SQLException{
		return employeeDAO.restoreEmployee(backupFilePath);
	}
}

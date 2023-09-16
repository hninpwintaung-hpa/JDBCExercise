package com.employee.dao;

import java.sql.SQLException;
import java.util.List;

import com.employee.model.Employee;

public interface EmployeeDAO {
	public int[] addEmployeeBatch(List<Employee> employeesList) throws SQLException;

	public void addEmployee(Employee employee) throws SQLException;

	public void updateEmployee(int empId, Employee employee) throws SQLException;

	public void deleteEmployee(int empId) throws SQLException;

	public List<Employee> findAllEmployee() throws SQLException;

	public List<Employee> getEmployeesByLastName(String lastName) throws SQLException;
	
	public List<Employee> getEmployeesByFirstName(String firstName) throws SQLException;
	
	public List<Employee> getAllEmployeeByAscHireDate() throws SQLException;
	
	public List<Employee> getAllEmployeeByDescHireDate() throws SQLException;

	public Employee getEmployeeById(int id) throws SQLException;
	
	public int getNumberOfTotalEmployee() throws SQLException;
	
	public String getAllEmployeeAvgHireDate()throws SQLException;
	
	public void updateEmployeesSalaryByBatch(Object[][] employeesToUpdate) throws SQLException;
	
	public Boolean employeeBackup() throws SQLException;
	
	public Boolean restoreEmployee(String backupFile) throws SQLException;


}

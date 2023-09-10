package com.employee.service;

import java.sql.SQLException;
import java.util.List;

import com.employee.model.Employee;

public interface EmployeeService {

	public int[] addEmployeeBatch(List<Employee> employees) throws SQLException;

	public void insertEmplyoee(Employee employee) throws SQLException;

	public void updateEmployee(int empId, Employee employee) throws SQLException;

	public List<Employee> getAllEmployee() throws SQLException;

	public List<Employee> getEmployeesByLastName(String lastName) throws SQLException;

	public void deleteEmployee(int empId) throws SQLException;

	public Employee getEmployeeById(int id) throws SQLException;

}

package com.employee.service;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeService {

	public void insertEmplyoee(Employee employee);

	public void updateEmployee(int empId, Employee employee);

	public List<Employee> getAllEmployee();

	public List<Employee> getEmployeesByLastName(String lastName);

	public void deleteEmployee(int empId);

	public Employee getEmployeeById(int id);

}

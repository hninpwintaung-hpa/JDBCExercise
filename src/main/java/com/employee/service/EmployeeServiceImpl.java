package com.employee.service;

import java.util.List;

import com.employee.dao.EmployeeDAO;
import com.employee.dao.EmployeeDAOImpl;
import com.employee.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	public void insertEmplyoee(Employee employee) {
		employeeDAO.addEmployee(employee);
	}

	public void updateEmployee(int empId, Employee employee) {
		employeeDAO.updateEmployee(empId, employee);
	}

	public List<Employee> getAllEmployee() {
		return employeeDAO.findAllEmployee();
	}

	public List<Employee> getEmployeesByLastName(String lastName) {
		return employeeDAO.getEmployeesByLastName(lastName);
	}

	public void deleteEmployee(int empId) {
		employeeDAO.deleteEmployee(empId);
	}

	public Employee getEmployeeById(int id) {
		return employeeDAO.getEmployeeById(id);
	}
}

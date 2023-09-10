package com.employee.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.EmployeeServiceImpl;

public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImpl();
		EmployeeController employeeController = new EmployeeController(employeeService);

		// employeeController.addEmployee();

		// employeeController.findAllEmployee();

		// employeeController.findEmployeeByLastName("Kim");

		// employeeController.updateEmployee();
		//employeeController.deleteEmployee();
		
		employeeController.addEmployeeBatch();
	}

	public void addEmployeeBatch() {
		List<Employee> employeesToInsert = new ArrayList<>();

		Employee alice = new Employee();
		alice.setEmployeeId(3);
		alice.setFirstName("alice");
		alice.setLastName("Johnson");
		alice.setEmail("alice@gmail.com");
		alice.setHireDate("2023-06-15");

		Employee eve = new Employee();
		eve.setEmployeeId(5);
		eve.setFirstName("Eve");
		eve.setLastName("Smith");
		eve.setEmail("eve.smith@example.com");
		eve.setHireDate("2022-12-05");

		employeesToInsert.add(alice);
		employeesToInsert.add(eve);

		try {
			
			int[] result = employeeService.addEmployeeBatch(employeesToInsert);
            System.out.println("Batch insert successful. Rows inserted: " + result.length);
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addEmployee() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter employee ID:");
		int employeeId = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter first name:");
		String firstName = scanner.nextLine();

		System.out.println("Enter last name:");
		String lastName = scanner.nextLine();

		System.out.println("Enter email:");
		String email = scanner.nextLine();

		System.out.println("Enter hire date (YYYY-MM-DD):");
		String hireDate = scanner.nextLine();

		Employee newEmployee = new Employee();
		newEmployee.setEmployeeId(employeeId);
		newEmployee.setFirstName(firstName);
		newEmployee.setLastName(lastName);
		newEmployee.setEmail(email);
		newEmployee.setHireDate(hireDate);

		try {
			employeeService.insertEmplyoee(newEmployee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scanner.close();
	}

	public void findAllEmployee() {
		List<Employee> result;
		try {
			result = employeeService.getAllEmployee();

			for (Employee employee : result) {
				System.out.print(employee.getEmployeeId() + ", ");
				System.out.print(employee.getFirstName() + " ");
				System.out.print(employee.getLastName() + ", ");
				System.out.print(employee.getEmail() + ", ");
				System.out.print(employee.getHireDate() + ", ");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void findEmployeeByLastName(String lastName) {
		List<Employee> result;
		try {
			result = employeeService.getEmployeesByLastName(lastName);

			for (Employee employee : result) {
				System.out.print(employee.getEmployeeId() + ", ");
				System.out.print(employee.getFirstName() + " ");
				System.out.print(employee.getLastName() + ", ");
				System.out.print(employee.getEmail() + ", ");
				System.out.print(employee.getHireDate() + ", ");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateEmployee() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Employee ID you want to update:");
		int employeeId = scanner.nextInt();
		scanner.nextLine();

		Employee existingEmployee;
		try {
			existingEmployee = employeeService.getEmployeeById(employeeId);
			
			if (existingEmployee == null) {
				System.out.println("Employee not found.");
			} else {
				System.out.println("Enter new first name:");
				String newFirstName = scanner.nextLine();

				System.out.println("Enter new last name:");
				String newLastName = scanner.nextLine();

				System.out.println("Enter new email:");
				String newEmail = scanner.nextLine();

				System.out.println("Enter new hire date (YYYY-MM-DD):");
				String newHireDate = scanner.nextLine();

				existingEmployee.setFirstName(newFirstName);
				existingEmployee.setLastName(newLastName);
				existingEmployee.setEmail(newEmail);
				existingEmployee.setHireDate(newHireDate);

				employeeService.updateEmployee(employeeId, existingEmployee);
				System.out.println("Employee information updated successfully.");
				scanner.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteEmployee() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Employee ID you want to delete:");
		int employeeId = scanner.nextInt();
		scanner.nextLine();

		Employee existingEmployee;
		try {
			existingEmployee = employeeService.getEmployeeById(employeeId);
			
			if (existingEmployee == null) {
				System.out.println("Employee not found.");
			} else {
				employeeService.deleteEmployee(employeeId);
				System.out.println("Employee information deleted successfully.");

			}

			scanner.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

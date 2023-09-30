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

		// employeeController.deleteEmployee();

		  employeeController.addEmployeeBatch();

		// employeeController.findEmployeeByFirstName("Ev");

		// employeeController.getAllEmployeeByAscHireDate();

		// employeeController.getEmployeeByHireDate("ASC");

		// employeeController.getNumberOfTotalEmployee();

		///employeeController.getAllEmployeeAvgHireDate();
		
		 employeeController.updateEmployeesSalaryByBatch();
		 //employeeController.employeeBackup();
		
		//employeeController.restoreEmployee();
	}

	public void addEmployeeBatch() {
		List<Employee> employeesToInsert = new ArrayList<>();

		Employee alice = new Employee();
		alice.setEmployeeId(1);
		alice.setFirstName("Olivia");
		alice.setLastName("Johnson");
		alice.setEmail("olivia@gmail.com");
		alice.setHireDate("2020-06-15");

		Employee eve = new Employee();
		eve.setEmployeeId(2);
		eve.setFirstName("Rosie");
		eve.setLastName("William");
		eve.setEmail("rosie@example.com");
		eve.setHireDate("2021-12-05");

		employeesToInsert.add(alice);
		employeesToInsert.add(eve);

		try {

			int[] result = employeeService.addEmployeeBatch(employeesToInsert);
			System.out.println("Batch insert successful. Rows inserted: " + result.length);

		} catch (SQLException e) {
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

	public void findEmployeeByFirstName(String firstName) {
		List<Employee> result;
		try {
			result = employeeService.getEmployeesByFirstName(firstName);

			if (result != null) {

				for (Employee employee : result) {
					System.out.print(employee.getEmployeeId() + ", ");
					System.out.print(employee.getFirstName() + " ");
					System.out.print(employee.getLastName() + ", ");
					System.out.print(employee.getEmail() + ", ");
					System.out.print(employee.getHireDate() + ", ");
					System.out.println();
				}
			} else {
				System.out.println("User not found!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getEmployeeByHireDate(String orderBy) {
		List<Employee> result;
		try {
			result = employeeService.getAllEmployeeByHireDate(orderBy);

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

	public void getAllEmployeeByDescHireDate() {
		List<Employee> result;
		try {
			result = employeeService.getAllEmployeeByDescHireDate();

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

	public void getNumberOfTotalEmployee() {
		try {

			int totalEmployee = employeeService.getNumberOfTotalEmployee();

			System.out.println("The number of total employee :" + totalEmployee);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getAllEmployeeAvgHireDate() {
		try {
			String avgHireDate = employeeService.getAllEmployeeAvgHireDate();
			System.out.println("The average hire date of all employee :" + avgHireDate);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployeesSalaryByBatch() {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee1 = new Employee();
		employee1.setEmployeeId(3);
		employee1.setSalary(35000);
		
		Employee employee2 = new Employee();
		employee2.setEmployeeId(5);
		employee2.setSalary(30000);
		
		employeeList.add(employee1);
		employeeList.add(employee2);
		
        // Object[][] employeesToUpdate = {{1,30000},{2,45000},{3,35000},{5,30000}};
         try {
        	 employeeService.updateEmployeesSalaryByBatch(employeeList);
         } catch (SQLException e) {
 			e.printStackTrace();
 		}
	}
	
	public void employeeBackup() {
		 try {
        	 boolean result = employeeService.employeeBackup();
        	 if(result) {
                 System.out.println("Backup successful.");
        	 }
         } catch (SQLException e) {
 			e.printStackTrace();
 		}
	}
	
	public void restoreEmployee() {
		 String backupFilePath = "D:/employee_backup.txt";
		 try {
        	 boolean result = employeeService.restoreEmployee(backupFilePath);
        	 if(result) {
                 System.out.println("Restore successfully.");
        	 }
         } catch (SQLException e) {
 			e.printStackTrace();
 		}
	}
}

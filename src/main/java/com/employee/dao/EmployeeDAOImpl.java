package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.dbConn.DBConnection;
import com.employee.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	public static Connection conn;
	public static String sqlInsert = "insert into employee(employee_id,first_name,last_name,email,hire_date) values(?,?,?,?,?)";
	public static String sqlSelectAll = "select * from employee";
	public static String sqlSearchByName = "select * from employee where last_name = ?";
	public static String sqlSearchById = "select * from employee where employee_id = ?";
	public static String sqlUpdate = "update employee set first_name=?, last_name =? , email =?, hire_date =? where employee_id =? ";
	public static String sqlDelete = " delete from employee where employee_id =?";

	static {
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int[] addEmployeeBatch(List<Employee> employeesList) {

		int[] batchResult = null;
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert)) {

			for (Employee employee : employeesList) {

				preparedStatement.setInt(1, employee.getEmployeeId());
				preparedStatement.setString(2, employee.getFirstName());
				preparedStatement.setString(3, employee.getLastName());
				preparedStatement.setString(4, employee.getEmail());
				preparedStatement.setString(5, employee.getHireDate());

				preparedStatement.addBatch();
			}

			batchResult = preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return batchResult;

	}

	public void addEmployee(Employee employee) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert)) {

			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getEmail());
			preparedStatement.setString(5, employee.getHireDate());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(int empId, Employee employee) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getHireDate());
			preparedStatement.setInt(5, employee.getEmployeeId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int empId) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete)) {

			preparedStatement.setInt(1, empId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> findAllEmployee() {

		List<Employee> employeesList = new ArrayList<>();

		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectAll)) {

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(result.getInt("employee_id"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				employee.setEmail(result.getString("email"));
				employee.setHireDate(result.getString("hire_date"));

				employeesList.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeesList;
	}

	public List<Employee> getEmployeesByLastName(String lastName) {

		List<Employee> employeesList = new ArrayList<>();
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSearchByName)) {

			preparedStatement.setString(1, lastName);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(result.getInt("employee_id"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				employee.setEmail(result.getString("email"));
				employee.setHireDate(result.getString("hire_date"));

				employeesList.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeesList;
	}

	public Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSearchById)) {

			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				employee.setEmployeeId(result.getInt("employee_id"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				employee.setEmail(result.getString("email"));
				employee.setHireDate(result.getString("hire_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

}

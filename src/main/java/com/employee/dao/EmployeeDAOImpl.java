package com.employee.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employee.dbConn.DBConnection;
import com.employee.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	public static String sqlInsert = "insert into employee(employee_id,first_name,last_name,email,hire_date,salary) values(?,?,?,?,?,?)";
	public static String sqlSelectAll = "select * from employee";
	public static String sqlSearchByName = "select * from employee where last_name = ?";
	public static String sqlSearchById = "select * from employee where employee_id = ?";
	public static String sqlUpdate = "update employee set first_name=?, last_name =? , email =?,salary =?, hire_date =? where employee_id =? ";
	public static String sqlDelete = " delete from employee where employee_id =?";
	public static String sqlSearchByFirstName = "select * from employee where first_name = ?";
	public static String sqlSelectByDate = "SELECT * FROM employee ORDER BY "
			+ "CASE WHEN ? = 'ASC' THEN hire_date ELSE NULL END ASC, "
			+ "CASE WHEN ? = 'DESC' THEN hire_date ELSE NULL END DESC";
	public static String sqlSelectByDescDate = "select * from employee ORDER BY hire_date DESC";
	public static String sqlSelectCount = "select COUNT(*) from employee";
	public static String sqlCalculateAvgHiredate = "Select DATEADD(DAY, AVG(DATEDIFF(DAY, '19000101', hire_date)), '19000101') from employee";
	public static String sqlUpdateEmployeeSalary = "update employee set salary = ? where employee_id =?";
	private static Connection conn;

	static {
		try {
			conn = DBConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int[] addEmployeeBatch(List<Employee> employeesList) throws SQLException{

		int[] batchResult = null;
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert)) {

			for (Employee employee : employeesList) {

				preparedStatement.setInt(1, employee.getEmployeeId());
				preparedStatement.setString(2, employee.getFirstName());
				preparedStatement.setString(3, employee.getLastName());
				preparedStatement.setString(4, employee.getEmail());
				preparedStatement.setString(5, employee.getHireDate());
				preparedStatement.setFloat(6, (float) employee.getSalary());

				preparedStatement.addBatch();
			}

			batchResult = preparedStatement.executeBatch();

		} catch (SQLException e) {
	        throw new SQLException(e);
	        }
		return batchResult;

	}

	public void addEmployee(Employee employee) throws SQLException{
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert)) {

			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getEmail());
			preparedStatement.setString(6, employee.getHireDate());
			preparedStatement.setFloat(5, (float) employee.getSalary());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
	        throw new SQLException(e);
		}
	}

	public void updateEmployee(int empId, Employee employee) throws SQLException{
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getHireDate());
			preparedStatement.setFloat(5, (float) employee.getSalary());
			preparedStatement.setInt(6, employee.getEmployeeId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
	        throw new SQLException(e);
		}
	}

	public void deleteEmployee(int empId) throws SQLException{
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete)) {

			preparedStatement.setInt(1, empId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
	        throw new SQLException(e);
		}
	}

	public List<Employee> findAllEmployee() throws SQLException{

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
	        throw new SQLException(e);
		}
		return employeesList;
	}

	public List<Employee> getEmployeesByLastName(String lastName) throws SQLException{

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
	        throw new SQLException(e);
		}
		return employeesList;
	}

	public List<Employee> getEmployeesByFirstName(String firstName) throws SQLException{

		List<Employee> employeesList = new ArrayList<>();
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSearchByFirstName)) {

			preparedStatement.setString(1, firstName);
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
	        throw new SQLException(e);
		}
		return employeesList;
	}

	public List<Employee> getAllEmployeeByHireDate(String orderBy) throws SQLException{
		List<Employee> employeesList = new ArrayList<>();
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectByDate)) {

			preparedStatement.setString(1, orderBy);
			preparedStatement.setString(2, orderBy);

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
	        throw new SQLException(e);
		}
		return employeesList;

	}

	public List<Employee> getAllEmployeeByDescHireDate() throws SQLException{
		List<Employee> employeesList = new ArrayList<>();
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectByDescDate)) {

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
	        throw new SQLException(e);
		}
		return employeesList;

	}

	public Employee getEmployeeById(int id) throws SQLException{
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
	        throw new SQLException(e);
		}
		return employee;
	}

	public int getNumberOfTotalEmployee() throws SQLException{
		int total = 0;
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectCount)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				total = resultSet.getInt(1);
			}
		} catch (SQLException e) {
	        throw new SQLException(e);
		}
		return total;
	}

	public String getAllEmployeeAvgHireDate() throws SQLException{
		String avgHireDate = "";
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlCalculateAvgHiredate)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				avgHireDate = resultSet.getString(1);
			}
		} catch (SQLException e) {
	        throw new SQLException(e);
		}
		return avgHireDate;
	}

	public void updateEmployeesSalaryByBatch(List<Employee> employeesToUpdate) throws SQLException{
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdateEmployeeSalary)) {

			for (Employee employee : employeesToUpdate) {
				int id = employee.getEmployeeId();
				double salary = employee.getSalary();

				preparedStatement.setDouble(1, salary);
				preparedStatement.setInt(2, id);

				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
	        throw new SQLException(e);
		}
	}

	public Boolean employeeBackup() throws SQLException{
		Boolean result = false;
		String backupFilePath = "D:/employee_backup.txt";
		try (PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectAll);
				BufferedWriter writer = new BufferedWriter(new FileWriter(backupFilePath))) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int employeeId = resultSet.getInt("employee_id");
				String employeefName = resultSet.getString("first_name");
				String employeelName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String hireDate = resultSet.getString("hire_date");
				double salary = resultSet.getDouble("salary");

				String backupData = String.format("%d,%s,%s,%s,%s,%.2f%n", employeeId, employeefName, employeelName,
						email, hireDate, salary);
				writer.write(backupData);

			}
			result = true;

		} catch (SQLException | IOException e) {
	        throw new SQLException(e);
		}
		return result;
	}

	public Boolean restoreEmployee(String backupFilePath) throws SQLException{
		Boolean result = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(backupFilePath))) {

			String line;
			List<Employee> employeeList = new ArrayList<>();

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");

				System.out.println("Lenght of values :" + values.length);

				Employee employee = new Employee();

				employee.setEmployeeId(Integer.parseInt(values[0]));
				employee.setFirstName(values[1]);
				employee.setLastName(values[2]);
				employee.setEmail(values[3]);
				employee.setHireDate(values[4]);
				employee.setSalary(Double.parseDouble(values[5]));

				employeeList.add(employee);
			}

			addEmployeeBatch(employeeList);
			result = true;

		} catch (IOException e) {
	        throw new SQLException(e);
		}
		return result;
	}
}

package com.springapp.productspring.dto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.springapp.productspring.utility.DbUtility;

public class EmployeeDao implements EmployeeContract<Employee, Integer> {
	
	@Override
	public List<Employee> getRecords() throws Exception {
		
		Connection connection=null;
		Statement statement =null;
		ResultSet resultSet =null;
		List<Employee> list=null;
		
		try {
			connection=DbUtility.createConnection();
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select * from emptable");

			if(resultSet.next()) {
				list=new ArrayList<Employee>();
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				double salary=resultSet.getDouble("salary");
				
				Employee employee = new Employee(id, name, salary);
				list.add(employee);
				
			}
		} catch (ClassNotFoundException e) {
			throw e;
			
		} catch (SQLException e) {
			throw e;
			
		} catch (Exception e) {
			throw e;
			
		}
		return list;

	}

	@Override
	public Employee getRecord(Integer id) throws Exception {
		Connection connection=null;
		PreparedStatement statement =null;
		Employee employee = null;
		
		try {
			connection=DbUtility.createConnection();
			statement=connection.prepareStatement("select * from emptable where id=?");
			statement.setInt(1, id);
			
			ResultSet executeQuery = statement.executeQuery();
			
			if(executeQuery!=null) {
				int eid=executeQuery.getInt("id");
				String ename=executeQuery.getString("name");
				double esalary=executeQuery.getDouble("salary");
			}

			
		} catch (ClassNotFoundException e) {
			throw e;
			
		} catch (SQLException e) {
			throw e;
			
		} catch (Exception e) {
			throw e;
			
		}
		return employee;

	}

	@Override
	public Integer addRecord(Employee modelObject) throws Exception {
		Connection connection=null;
		PreparedStatement statement =null;
		Employee employee = null;
		
		try {
			connection=DbUtility.createConnection();
			statement=connection.prepareStatement("insert into emptable (id,name,salary) values(?,?,?)");
			statement.setInt(1, modelObject.getId());
			statement.setString(1, modelObject.getName());
			statement.setDouble(1, modelObject.getSalary());
			
			int res=statement.executeUpdate();
			
			return res;

		} catch (ClassNotFoundException e) {
			throw e;
			
		} catch (SQLException e) {
			throw e;
			
		} catch (Exception e) {
			throw e;
			
		}

	}

	@Override
	public Integer updateRecords(Integer id, Employee modelObject) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbUtility.createConnection();
			statement = connection.prepareStatement("update emptable where name=?,salary=? WHERE id=?");
			statement.setString(1, modelObject.getName());
			statement.setDouble(2, modelObject.getSalary());
			statement.setInt(3, id);
			return statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	@Override
	public Integer deleteRecord(Integer id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbUtility.createConnection();
			statement = connection.prepareStatement("delete from emptable where id=?");
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

}

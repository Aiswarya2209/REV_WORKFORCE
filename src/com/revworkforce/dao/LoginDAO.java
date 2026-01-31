package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.model.Employee;
import com.revworkforce.util.DBConnectionUtil;

public class LoginDAO {

	public Employee Login(int empId, String password) {

	    Employee emp = null;

	    String sql =
	        "SELECT EMPLOYEE_ID, NAME, ROLE " +
	        "FROM EMPLOYEE " +
	        "WHERE EMPLOYEE_ID = ? AND PASSWORD = ? AND ACTIVE_STATUS = 'Y'";

	    try {
	        Connection con = DBConnectionUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, empId);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            emp = new Employee();
	            emp.setEmployeeId(rs.getInt("EMPLOYEE_ID")); 
	            emp.setName(rs.getString("NAME"));
	            emp.setRole(rs.getString("ROLE"));
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return emp;
	}

}
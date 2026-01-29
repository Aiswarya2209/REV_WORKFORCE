
package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.model.Employee;
import com.revworkforce.util.DBConnectionUtil;

public class LoginDAO {

    public Employee login(int empId, String password) {
        Employee emp = null;
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT employee_id, name, role, manager_id FROM EMPLOYEE WHERE employee_id=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee();
                emp.setEmpId(rs.getInt("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}
package com.revworkforce.dao;
import java.sql.*;
import com.revworkforce.util.DBConnectionUtil;

public class AdminDao {

    public void insertEmployee(int id, String name, String email, String pass, String role) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "INSERT INTO EMPLOYEE (employee_id, name, email, password, role, active_status) " +
                         "VALUES (?, ?, ?, ?, ?, 'Y')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, role);
            ps.executeUpdate();

            System.out.println("Employee Added Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllEmployees() {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT employee_id, name, role, manager_id FROM EMPLOYEE";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- All Employees ---");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) +
                                   " | " + rs.getString(3) +
                                   " | Manager: " + rs.getInt(4));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateManager(int empId, int mgrId) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "UPDATE EMPLOYEE SET manager_id=? WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mgrId);
            ps.setInt(2, empId);
            ps.executeUpdate();

            System.out.println("Manager Assigned Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deactivate(int empId) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "UPDATE EMPLOYEE SET active_status='N' WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.executeUpdate();

            System.out.println("Employee Deactivated Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.revworkforce.dao;
import java.sql.*;
import com.revworkforce.util.DBConnectionUtil;

public class AdminDao {

	public boolean insertEmployee(int id, String name, String email, String pass, String role) {

	    Connection con = null;
	    PreparedStatement ps = null;

	    String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, NAME, EMAIL, PASSWORD, ROLE, ACTIVE_STATUS) " +
	                 "VALUES (?, ?, ?, ?, ?, 'Y')";

	    try {
	        con = DBConnectionUtil.getConnection();
	        ps = con.prepareStatement(sql);

	        ps.setInt(1, id);
	        ps.setString(2, name);
	        ps.setString(3, email);
	        ps.setString(4, pass);
	        ps.setString(5, role.toUpperCase());

	        ps.executeUpdate();
	        return true;

	    } catch (java.sql.SQLIntegrityConstraintViolationException e) {
	        
	        System.out.println(" Email already exists. Please use a unique email.");
	        return false;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;

	    } finally {
	        try { if (ps != null) ps.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
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
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deactivate(int empId) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "UPDATE EMPLOYEE SET ACTIVE_STATUS='N' WHERE employee_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.executeUpdate();

            System.out.println("Employee Deactivated Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean activateEmployee(int empId) {

        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE EMPLOYEE SET ACTIVE_STATUS='N' WHERE employee_id=?";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, empId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
    

    
 
    public boolean addHoliday(String name, String date, String description) {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnectionUtil.getConnection();

            String sql =
                "INSERT INTO HOLIDAY " +
                "(HOLIDAY_ID, HOLIDAY_NAME, HOLIDAY_DATE, DESCRIPTION) " +
                "VALUES (HOLIDAY_SEQ.NEXTVAL, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDate(2, java.sql.Date.valueOf(date)); // YYYY-MM-DD
            ps.setString(3, description);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}





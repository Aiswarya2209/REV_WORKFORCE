package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revworkforce.model.Employee;
import com.revworkforce.model.Holiday;
import com.revworkforce.util.DBConnectionUtil;
import com.revworkforce.model.PerformanceReview;

import java.security.MessageDigest;


public class EmployeeDao {

    
	public Employee getEmployeeById(int employeeId) {

	    Employee employee = null;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String sql = "SELECT employee_id, name, email, phone, role, department " +
	                 "FROM EMPLOYEE WHERE employee_id = ?";

	    try {
	        con = DBConnectionUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, employeeId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            employee = new Employee();
	            employee.setEmployeeId(rs.getInt("employee_id"));
	            employee.setName(rs.getString("name"));
	            employee.setEmail(rs.getString("email"));
	            employee.setPhone(rs.getString("phone"));
	            employee.setRole(rs.getString("role"));
	            employee.setDepartment(rs.getString("department"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(rs, ps, con);
	    }

	    return employee;
	}


    
    public boolean updateProfile(int empId, String email, String phone) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE EMPLOYEE SET email = ?, phone = ? WHERE employee_id = ?";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
            ps.setInt(3, empId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, ps, con);
        }
    }

   
    public Employee getReportingManager(int empId) {
        Employee mgr = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql =
            "SELECT m.employee_id, m.name, m.email, m.phone, m.role, m.department " +
            "FROM EMPLOYEE e JOIN EMPLOYEE m ON e.manager_id = m.employee_id " +
            "WHERE e.employee_id = ?";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            rs = ps.executeQuery();

            if (rs.next()) {
                mgr = new Employee();
                mgr.setEmployeeId(rs.getInt("employee_id"));
                mgr.setName(rs.getString("name"));
                mgr.setEmail(rs.getString("email"));
                mgr.setPhone(rs.getString("phone"));
                mgr.setRole(rs.getString("role"));
                mgr.setDepartment(rs.getString("department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, con);
        }
        return mgr;
    }

    
    public List<Holiday> getHolidayCalendar() {
        List<Holiday> holidays = new ArrayList<Holiday>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT holiday_date, holiday_name FROM HOLIDAY_CALENDAR ORDER BY holiday_date";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                holidays.add(
                    new Holiday(
                        rs.getDate("holiday_date"),
                        rs.getString("holiday_name")
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, con);
        }
        return holidays;
    }

   
    public boolean changePassword(int empId, String oldPwd, String newPwd) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE EMPLOYEE SET password = ? WHERE employee_id = ? AND password = ?";

        String oldHash = hashPassword(oldPwd);
        String newHash = hashPassword(newPwd);

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, newHash);
            ps.setInt(2, empId);
            ps.setString(3, oldHash);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, ps, con);
        }
    }

   

        public List<PerformanceReview> getMyPerformanceReview(int empId) {

            List<PerformanceReview> list = new ArrayList<PerformanceReview>();

            String sql =
                "SELECT REVIEW_YEAR, SELF_RATING, MANAGER_RATING, MANAGER_FEEDBACK " +
                "FROM PERFORMANCE_REVIEW WHERE EMPLOYEE_ID = ?";

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                con = DBConnectionUtil.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, empId);

                rs = ps.executeQuery();

                while (rs.next()) {
                    PerformanceReview pr = new PerformanceReview();
                    pr.setReviewYear(rs.getInt("REVIEW_YEAR"));
                    pr.setSelfRating(rs.getInt("SELF_RATING"));
                    pr.setManagerRating(rs.getInt("MANAGER_RATING"));
                    pr.setManagerFeedback(rs.getString("MANAGER_FEEDBACK"));

                    list.add(pr);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) {}
                try { if (ps != null) ps.close(); } catch (Exception e) {}
                try { if (con != null) con.close(); } catch (Exception e) {}
            }

            return list;
        }
    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    private void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
    }
}

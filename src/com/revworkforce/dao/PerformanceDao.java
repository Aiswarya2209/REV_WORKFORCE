package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revworkforce.util.DBConnectionUtil;

public class PerformanceDao {

    
    public void submitReview(int empId, int year, String achievements,
                             String improvements, int selfRating) {

        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO PERFORMANCE_REVIEW " +
                     "(REVIEW_ID, EMPLOYEE_ID, REVIEW_YEAR, ACHIEVEMENTS, IMPROVEMENTS, SELF_RATING, " +
                     "MANAGER_FEEDBACK, MANAGER_RATING, STATUS) " +
                     "VALUES (SEQ_PERF_REVIEW.NEXTVAL, ?, ?, ?, ?, ?, NULL, NULL, 'SUBMITTED')";

        try {
            conn = DBConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, empId);
            ps.setInt(2, year);
            ps.setString(3, achievements);
            ps.setString(4, improvements);
            ps.setInt(5, selfRating);

            ps.executeUpdate();
            System.out.println("Performance review submitted successfully");

        } catch (SQLException e) {
            System.out.println("SQL ERROR CODE : " + e.getErrorCode());
            System.out.println("SQL MESSAGE    : " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void viewMyReview(int empId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT REVIEW_YEAR, ACHIEVEMENTS, IMPROVEMENTS, SELF_RATING, " +
                     "MANAGER_FEEDBACK, MANAGER_RATING, STATUS " +
                     "FROM PERFORMANCE_REVIEW WHERE EMPLOYEE_ID=?";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);

            rs = ps.executeQuery();

            System.out.println("\n===== MY PERFORMANCE REVIEWS =====");
            while (rs.next()) {
                System.out.println("Year              : " + rs.getInt("REVIEW_YEAR"));
                System.out.println("Achievements      : " + rs.getString("ACHIEVEMENTS"));
                System.out.println("Improvements      : " + rs.getString("IMPROVEMENTS"));
                System.out.println("Self Rating       : " + rs.getInt("SELF_RATING"));
                System.out.println("Manager Feedback  : " + rs.getString("MANAGER_FEEDBACK"));
                System.out.println("Manager Rating    : " + rs.getInt("MANAGER_RATING"));
                System.out.println("Status            : " + rs.getString("STATUS"));
                System.out.println("----------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public void viewTeamReviews(int managerId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT pr.review_id, e.name, pr.review_year, pr.status " +
                     "FROM PERFORMANCE_REVIEW pr " +
                     "JOIN EMPLOYEE e ON pr.employee_id = e.employee_id " +
                     "WHERE e.manager_id = ?";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, managerId);

            rs = ps.executeQuery();

            System.out.println("\n--- Team Performance Reviews ---");
            while (rs.next()) {
                System.out.println("Review ID : " + rs.getInt(1) +
                                   " | Employee : " + rs.getString(2) +
                                   " | Year : " + rs.getInt(3) +
                                   " | Status : " + rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public void reviewEmployee(int reviewId, String feedback, int rating) {

        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE PERFORMANCE_REVIEW " +
                     "SET MANAGER_FEEDBACK=?, MANAGER_RATING=?, STATUS='REVIEWED' " +
                     "WHERE REVIEW_ID=?";

        try {
            con = DBConnectionUtil.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, feedback);
            ps.setInt(2, rating);
            ps.setInt(3, reviewId);

            ps.executeUpdate();
            System.out.println(" Performance review updated");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.revworkforce.dao;

import java.sql.*;
import com.revworkforce.util.DBConnectionUtil;

	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	

	
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

	

    // Manager views team reviews
    public void viewTeamReviews(int managerId) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT pr.review_id, e.name, pr.review_year, pr.status " +
                         "FROM PERFORMANCE_REVIEW pr JOIN EMPLOYEE e " +
                         "ON pr.employee_id = e.employee_id " +
                         "WHERE e.manager_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Team Performance Reviews ---");
            while (rs.next()) {
                System.out.println("Review ID: " + rs.getInt(1) +
                                   " | Employee: " + rs.getString(2) +
                                   " | Year: " + rs.getInt(3) +
                                   " | Status: " + rs.getString(4));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Manager gives feedback
    public void reviewEmployee(int reviewId, String feedback, int rating) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "UPDATE PERFORMANCE_REVIEW " +
                         "SET MANAGER_FEEDBACK=?, MANAGER_RATING=?, STATUS='REVIEWED' " +
                         "WHERE REVIEW_ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, feedback);
            ps.setInt(2, rating);
            ps.setInt(3, reviewId);
            ps.executeUpdate();

            System.out.println("Performance Review Updated.");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

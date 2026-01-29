package com.revworkforce.dao;

import java.sql.*;
import com.revworkforce.util.DBConnectionUtil;

public class GoalDao {

    // Employee adds goal
	public void addGoal(int empId, String desc, String deadline, String priority, String metrics) {
	    try {
	        Connection con = DBConnectionUtil.getConnection();

	        String sql = "INSERT INTO GOALS " +
	                     "(GOAL_ID, EMPLOYEE_ID, DESCRIPTION, DEADLINE, PRIORITY, PROGRESS, STATUS) " +
	                     "VALUES (SEQ_GOAL.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, 0, 'NOT_STARTED')";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, empId);
	        ps.setString(2, desc);
	        ps.setString(3, deadline);
	        ps.setString(4, priority.toUpperCase());  // HIGH/MEDIUM/LOW

	        ps.executeUpdate();
	        System.out.println("Goal Added Successfully.");

	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    // View employee goals
	public void viewGoals(int empId) {
	    try {
	        Connection con = DBConnectionUtil.getConnection();

	        String sql = "SELECT goal_id, description, deadline, priority, status " +
	                     "FROM GOALS WHERE employee_id=?";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, empId);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n--- Your Goals ---");
	        while (rs.next()) {
	            System.out.println("ID: " + rs.getInt("goal_id") +
	                               " | " + rs.getString("description") +
	                               " | " + rs.getDate("deadline") +
	                               " | " + rs.getString("priority") +
	                               " | " + rs.getString("status"));
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	// Manager updates goal status
	public void updateGoalStatus(int goalId, String status) {
	    try {
	        Connection con = DBConnectionUtil.getConnection();

	        String sql = "UPDATE GOALS SET status=? WHERE goal_id=?";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, status);
	        ps.setInt(2, goalId);
	        ps.executeUpdate();

	        System.out.println("Goal Status Updated to " + status);
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

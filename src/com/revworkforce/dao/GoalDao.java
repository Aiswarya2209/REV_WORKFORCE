package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalDao {

    
    public ResultSet getMyGoals(int empId, Connection con) throws SQLException {
        String sql = "SELECT goal_id, description, deadline, priority, metrics, progress, status " +
                     "FROM GOALS WHERE employee_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, empId);
        return ps.executeQuery();
    }

   
    public boolean addGoal(int empId, String description, String deadline, String priority, String metrics, Connection con) throws SQLException {
        String sql = "INSERT INTO GOALS(employee_id, description, deadline, priority, metrics, progress, status) " +
                     "VALUES (?, ?, ?, ?, ?, 0, 'PENDING')";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, empId);
        ps.setString(2, description);
        ps.setString(3, deadline);
        ps.setString(4, priority);
        ps.setString(5, metrics);

        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
    }

    
    public boolean updateGoalProgress(int goalId, int empId, int progress, Connection con) throws SQLException {
        String sql = "UPDATE GOALS SET progress=?, status='IN_PROGRESS' WHERE goal_id=? AND employee_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, progress);
        ps.setInt(2, goalId);
        ps.setInt(3, empId);

        int rows = ps.executeUpdate();
        ps.close();
        return rows > 0;
    }
}

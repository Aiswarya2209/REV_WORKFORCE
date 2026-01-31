package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import com.revworkforce.util.DBConnectionUtil;

public class LeaveDao {

    
    public int getRemainingLeaves(int empId, int leaveTypeId) {
        int remaining = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionUtil.getConnection();
            String sql = "SELECT REMAINING_LEAVES FROM LEAVE_BALANCE WHERE EMPLOYEE_ID=? AND LEAVE_TYPE_ID=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setInt(2, leaveTypeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                remaining = rs.getInt("REMAINING_LEAVES");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return remaining;
    }

    
    public void viewMyLeaves(int empId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionUtil.getConnection();
            String sql = "SELECT LEAVE_ID, LEAVE_TYPE_ID, FROM_DATE, TO_DATE, STATUS " +
                         "FROM LEAVE_REQUEST " +
                         "WHERE EMPLOYEE_ID=? AND TO_DATE >= ? " +
                         "ORDER BY FROM_DATE";
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            rs = ps.executeQuery();

            System.out.println("\n--- My Leaves (Current & Upcoming) ---");
            while (rs.next()) {
                System.out.println("Leave ID      : " + rs.getInt("LEAVE_ID"));
                System.out.println("Leave Type ID : " + rs.getInt("LEAVE_TYPE_ID"));
                System.out.println("From          : " + rs.getDate("FROM_DATE"));
                System.out.println("To            : " + rs.getDate("TO_DATE"));
                System.out.println("Status        : " + rs.getString("STATUS"));
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            System.out.println("Unable to fetch leaves.");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    
    public boolean applyLeave(int empId, int leaveTypeId, Date fromDate, Date toDate, String reason) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnectionUtil.getConnection();
            String sql = "INSERT INTO LEAVE_REQUEST (EMPLOYEE_ID, LEAVE_TYPE_ID, FROM_DATE, TO_DATE, REASON, STATUS) " +
                         "VALUES (?, ?, ?, ?, ?, 'PENDING')";
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setInt(2, leaveTypeId);
            ps.setDate(3, fromDate);
            ps.setDate(4, toDate);
            ps.setString(5, reason);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    
    public void viewLeaveStatus(int empId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionUtil.getConnection();
            String sql = "SELECT LEAVE_ID, FROM_DATE, TO_DATE, STATUS " +
                         "FROM LEAVE_REQUEST " +
                         "WHERE EMPLOYEE_ID=? AND TO_DATE >= ? " +
                         "ORDER BY FROM_DATE";
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            rs = ps.executeQuery();

            System.out.println("\n--- Leave Status (Current & Upcoming) ---");
            while (rs.next()) {
                System.out.println(
                    "Leave ID: " + rs.getInt("LEAVE_ID") +
                    " | From: " + rs.getDate("FROM_DATE") +
                    " to " + rs.getDate("TO_DATE") +
                    " | Status: " + rs.getString("STATUS")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    
    public void viewLeaveBalance(int empId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionUtil.getConnection();
            String sql = "SELECT LEAVE_TYPE_ID, TOTAL_QUOTA, USED_LEAVES, REMAINING_LEAVES " +
                         "FROM LEAVE_BALANCE WHERE EMPLOYEE_ID=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            rs = ps.executeQuery();

            System.out.println("\n--- Leave Balance ---");
            while (rs.next()) {
                int typeId = rs.getInt("LEAVE_TYPE_ID");
                String type =
                        typeId == 1 ? "CL" :
                        typeId == 2 ? "SL" :
                        typeId == 3 ? "PL" : "UNKNOWN";

                System.out.println("Leave Type      : " + type);
                System.out.println("Total Quota     : " + rs.getInt("TOTAL_QUOTA"));
                System.out.println("Used Leaves     : " + rs.getInt("USED_LEAVES"));
                System.out.println("Remaining Leaves: " + rs.getInt("REMAINING_LEAVES"));
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
}

package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.util.DBConnectionUtil;

public class LeaveDao {

    public void viewLeaveBalance(int empId) {
        try {
            Connection con = DBConnectionUtil.getConnection();

            String sql = "SELECT lt.leave_name, lb.remaining_leaves " +
                         "FROM LEAVE_BALANCE lb JOIN LEAVE_TYPE lt " +
                         "ON lb.leave_type_id = lt.leave_type_id " +
                         "WHERE lb.employee_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Leave Balance ---");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " : " + rs.getInt(2));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyLeave(int empId, int leaveTypeId, String fromDate, String toDate, String reason) {
        try {
            Connection con = DBConnectionUtil.getConnection();

            String sql = "INSERT INTO LEAVE_REQUEST " +
                    "(leave_id, employee_id, leave_type_id, from_date, to_date, reason, status, applied_date) " +
                    "VALUES (seq_leave_request.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), " +
                    "TO_DATE(?, 'YYYY-MM-DD'), ?, 'PENDING', SYSDATE)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ps.setInt(2, leaveTypeId);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.setString(5, reason);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Leave Applied Successfully!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewLeaveStatus(int empId) {
        try {
            Connection con = DBConnectionUtil.getConnection();

            String sql = "SELECT leave_id, status, from_date, to_date " +
                         "FROM LEAVE_REQUEST WHERE employee_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Leave Status ---");
            while (rs.next()) {
                System.out.println("Leave ID: " + rs.getInt(1) +
                        " | " + rs.getString(2) +
                        " | " + rs.getDate(3) +
                        " to " + rs.getDate(4));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
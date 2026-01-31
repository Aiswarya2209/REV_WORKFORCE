package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.util.DBConnectionUtil;

public class ManagerDao {

    public void getTeamLeaveRequests(int managerId) {
        try {
            Connection con = DBConnectionUtil.getConnection();

            String sql = "SELECT lr.leave_id, e.name, lr.status, lr.from_date, lr.to_date " +
                         "FROM LEAVE_REQUEST lr JOIN EMPLOYEE e " +
                         "ON lr.employee_id = e.employee_id " +
                         "WHERE e.manager_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, managerId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Team Leave Requests ---");
            while (rs.next()) {
                System.out.println("Leave ID: " + rs.getInt(1) +
                                   " | Employee: " + rs.getString(2) +
                                   " | Status: " + rs.getString(3) +
                                   " | " + rs.getDate(4) +
                                   " to " + rs.getDate(5));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLeaveStatus(int leaveId, String status) {
        try {
            Connection con = DBConnectionUtil.getConnection();

            String sql1 = "UPDATE LEAVE_REQUEST SET status=? WHERE leave_id=?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1, status);
            ps1.setInt(2, leaveId);
            ps1.executeUpdate();

            String sql2 = "SELECT employee_id, leave_type_id FROM LEAVE_REQUEST WHERE leave_id=?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, leaveId);
            ResultSet rs = ps2.executeQuery();
            rs.next();
            int empId = rs.getInt(1);
            int leaveTypeId = rs.getInt(2);

            if (status.equalsIgnoreCase("APPROVED")) {
                String sql3 = "UPDATE LEAVE_BALANCE " +
                              "SET remaining_leaves = remaining_leaves - 1, " +
                              "used_leaves = used_leaves + 1 " +
                              "WHERE employee_id=? AND leave_type_id=?";
                PreparedStatement ps3 = con.prepareStatement(sql3);
                ps3.setInt(1, empId);
                ps3.setInt(2, leaveTypeId);
                ps3.executeUpdate();
            }

            String msg = "Your leave (ID: " + leaveId + ") is " + status;
            String sql4 = "INSERT INTO NOTIFICATION " +
                          "(NOTIFICATION_ID, EMPLOYEE_ID, MESSAGE, IS_READ, CREATED_AT) " +
                          "VALUES (SEQ_NOTIFICATION.NEXTVAL, ?, ?, 'N', SYSDATE)";

            PreparedStatement ps4 = con.prepareStatement(sql4);
            ps4.setInt(1, empId);
            ps4.setString(2, msg);
            ps4.executeUpdate();

            System.out.println("Leave " + status + " successfully and notification sent.");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
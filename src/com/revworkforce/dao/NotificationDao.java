package com.revworkforce.dao;

import java.sql.*;
import com.revworkforce.util.DBConnectionUtil;

public class NotificationDao {

    public void viewNotifications(int empId) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql =
            	    "SELECT MESSAGE, CREATED_AT " +
            	    "FROM NOTIFICATION " +
            	    "WHERE EMPLOYEE_ID = ? " +
            	    "ORDER BY CREATED_AT DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Notifications ---");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getDate(2));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

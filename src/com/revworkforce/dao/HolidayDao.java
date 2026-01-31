package com.revworkforce.dao;

import java.sql.*;
import com.revworkforce.util.DBConnectionUtil;

public class HolidayDao {

	public void viewHolidays() {

		String sql = "SELECT HOLIDAY_DATE, HOLIDAY_NAME, DESCRIPTION FROM SYSTEM.HOLIDAYS";




	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = DBConnectionUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        System.out.println("\n--- Holiday Calendar ---");

	        while (rs.next()) {
	            System.out.println("Date : " + rs.getDate("HOLIDAY_DATE"));
	            System.out.println("Name : " + rs.getString("HOLIDAY_NAME"));
	            System.out.println("Desc : " + rs.getString("DESCRIPTION"));
	            System.out.println("------------------------");
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
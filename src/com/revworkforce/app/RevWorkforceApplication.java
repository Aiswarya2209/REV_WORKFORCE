package com.revworkforce.app;

import java.sql.Connection;
import com.revworkforce.util.DBConnectionUtil;

public class RevWorkforceApplication {

    public static void main(String[] args) {

        Connection con = null;
        try {
            con = DBConnectionUtil.getConnection();
            System.out.println("Database Connected Successfully");
        } catch (Exception e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.revworkforce.service;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.util.Scanner;
import com.revworkforce.dao.AdminDao;
import com.revworkforce.util.DBConnectionUtil;
//import java.sql.Date;
public class AdminService {

    private AdminDao dao = new AdminDao();
    private Scanner sc = new Scanner(System.in);

    public void addEmployee() {
        System.out.print("Employee ID: ");
        int id = sc.nextInt();

        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Email: ");
        String email = sc.next();

        System.out.print("Password: ");
        String pass = sc.next();

        System.out.print("Role (EMPLOYEE/MANAGER): ");
        String role = sc.next().toUpperCase();

        dao.insertEmployee(id, name, email, pass, role);
        System.out.println("Employee added successfully.");
    }

    public void viewAllEmployees() {
        dao.getAllEmployees();
    }

    public void assignManager() {
        System.out.print("Employee ID: ");
        int empId = sc.nextInt();

        System.out.print("Manager ID: ");
        int mgrId = sc.nextInt();

        dao.updateManager(empId, mgrId);
        System.out.println("Manager assigned successfully.");
    }

    public void deactivateEmployee() {
        System.out.print("Enter Employee ID to deactivate: ");
        int empId = sc.nextInt();

        System.out.print("Confirm (YES/NO): ");
        String confirm = sc.next();

        if (confirm.equalsIgnoreCase("YES")) {
            dao.deactivate(empId);
            System.out.println("Employee deactivated.");
        } else {
            System.out.println("Deactivation cancelled.");
        }
    }

    public void activateEmployee() {
        System.out.print("Enter Employee ID to activate: ");
        int empId = sc.nextInt();

        boolean result = dao.activateEmployee(empId);

        if (result) {
            System.out.println("Employee activated successfully.");
        } else {
            System.out.println("Failed to activate employee.");
        }
    }

    

  

    
    public boolean addHoliday(String holidayName, String holidayDate, String description) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnectionUtil.getConnection();
            String sql = "INSERT INTO HOLIDAYS (HOLIDAY_NAME, HOLIDAY_DATE, DESCRIPTION) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, holidayName);

            
            try {
                ps.setDate(2, java.sql.Date.valueOf(holidayDate));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format: " + holidayDate);
                return false;
            }

            ps.setString(3, description);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    }



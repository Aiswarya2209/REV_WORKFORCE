package com.revworkforce.service;

import java.util.Scanner;
import com.revworkforce.dao.AdminDao;

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
    }

    public void deactivateEmployee() {
        System.out.print("Enter Employee ID to deactivate: ");
        int empId = sc.nextInt();

        System.out.print("Are you sure you want to deactivate this employee? (YES/NO): ");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("YES")) {
            dao.deactivate(empId);  
        } else {
            System.out.println("Deactivation Cancelled.");
        }
    }
}

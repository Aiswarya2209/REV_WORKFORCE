package com.revworkforce.main;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.AuthService;
import com.revworkforce.ui.EmployeeMenu;
import com.revworkforce.ui.ManagerMenu;
import com.revworkforce.ui.AdminMenu;

public class RevWorkForceApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();

        while (true) {
            System.out.println("\n===== Welcome to RevWorkForce HRMS =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice = sc.nextInt();

            if (mainChoice == 1) {

                System.out.println("\nLogin As:");
                System.out.println("1. Employee");
                System.out.println("2. Manager");
                System.out.println("3. Admin");
                System.out.print("Enter your choice: ");
                int roleChoice = sc.nextInt();

                System.out.print("Enter Employee ID: ");
                int empId = sc.nextInt();

                System.out.print("Enter Password: ");
                String password = sc.next();

                Employee emp = authService.authenticate(empId, password);

                if (emp == null) {
                    System.out.println("Invalid Login Credentials!");
                    continue;
                }

                System.out.println("\nLogin Successful!");
                System.out.println("Welcome " + emp.getName());
                System.out.println("Role: " + emp.getRole());

                if (roleChoice == 1 && emp.getRole().equalsIgnoreCase("EMPLOYEE")) {
                    EmployeeMenu.show(emp);
                } 
                else if (roleChoice == 2 && emp.getRole().equalsIgnoreCase("MANAGER")) {
                    ManagerMenu.show(emp);
                } 
                else if (roleChoice == 3 && emp.getRole().equalsIgnoreCase("ADMIN")) {
                    AdminMenu.show(emp);
                } 
                else {
                    System.out.println("Role mismatch! Please login with correct role.");
                }

            } else if (mainChoice == 2) {
                System.out.println("Thank you for using RevWorkForce HRMS!");
                break;
            } else {
                System.out.println("Invalid Choice. Try again.");
            }
        }

        sc.close();
    }
}
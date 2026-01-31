package com.revworkforce.main;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.LoginService;
import com.revworkforce.ui.EmployeeMenu;
import com.revworkforce.ui.ManagerMenu;
import com.revworkforce.ui.AdminMenu;

public class RevWorkForceApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginService loginService = new LoginService();
        boolean exitApp = false;

        while (!exitApp) {
            System.out.println("\n===== Welcome to RevWorkForce HRMS =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            if (mainChoice == 1) {
                System.out.println("\nSelect Role:");
                System.out.println("1. Employee");
                System.out.println("2. Manager");
                System.out.println("3. Admin");
                System.out.print("Enter choice: ");
                int roleChoice = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter ID: ");
                int empId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                Employee emp = loginService.login(empId, password);

                if (emp == null) {
                    System.out.println("Invalid credentials or inactive account!");
                    continue;
                }

                System.out.println("\nLogin Successful!");
                System.out.println("Welcome " + emp.getName());
                System.out.println("Role: " + emp.getRole());

                switch (roleChoice) {
                    case 1:
                        EmployeeMenu.show(emp);
                        break;
                    case 2:
                        ManagerMenu.show(emp);
                        break;
                    case 3:
                        AdminMenu.show(emp);
                        break;
                    default:
                        System.out.println("Invalid role choice!");
                }

            } else if (mainChoice == 2) {
                System.out.println("Thank you for using RevWorkForce HRMS!");
                exitApp = true;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}

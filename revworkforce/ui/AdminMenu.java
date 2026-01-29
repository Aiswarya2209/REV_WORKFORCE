package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.AdminService;

public class AdminMenu {

    public static void show(Employee admin) {
        Scanner sc = new Scanner(System.in);
        AdminService service = new AdminService();

        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Assign Manager");
            System.out.println("4. Deactivate Employee");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    service.addEmployee();
                    break;
                case 2:
                    service.viewAllEmployees();
                    break;
                case 3:
                    service.assignManager();
                    break;
                case 4:
                    service.deactivateEmployee();
                    break;
                case 5:
                    System.out.println("Admin Logout Successful!");
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}

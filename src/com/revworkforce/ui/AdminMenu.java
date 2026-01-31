package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.AdminService;
//import com.revworkforce.dao.HolidayDao;

public class AdminMenu {

    public static void show(Employee emp) {

        Scanner sc = new Scanner(System.in);
        AdminService adminService = new AdminService();
        //HolidayDao dao = new HolidayDao();

        int choice;

        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Assign Manager");
            System.out.println("4. Deactivate Employee");
            System.out.println("5. Activate Employee");
            System.out.println("6. Add Holiday");
            System.out.println("7. Logout");
            System.out.print("Choose: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    adminService.addEmployee();
                    break;

                case 2:
                    adminService.viewAllEmployees();
                    break;

                case 3:
                    adminService.assignManager();
                    break;

                case 4:
                    adminService.deactivateEmployee();
                    break;

                case 5:
                    adminService.activateEmployee();
                    break;

                case 6:
                    System.out.print("Holiday Name: ");
                    String name = sc.nextLine();

                    System.out.print("Holiday Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    boolean result = adminService.addHoliday(name, date, desc);


                    if (result) {
                        System.out.println("Holiday added successfully");
                    } else {
                        System.out.println("Failed to add holiday");
                    }
                    break; 

                case 7:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }
}

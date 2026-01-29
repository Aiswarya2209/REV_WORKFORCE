package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.LeaveService;
import com.revworkforce.dao.NotificationDao;
import com.revworkforce.service.PerformanceService;
import com.revworkforce.service.GoalService;

public class EmployeeMenu {

    public static void show(Employee emp) {
        Scanner sc = new Scanner(System.in);
        LeaveService leaveService = new LeaveService();

        while (true) {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1. View Profile");
            System.out.println("2. View Leave Balance");
            System.out.println("3. Apply Leave");
            System.out.println("4. View Leave Status");
            System.out.println("5. View Notifications");
            System.out.println("6. Submit Performance Review");
            System.out.println("7. Add Goal");
            System.out.println("8. View My Goals");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Employee ID : " + emp.getEmployeeId());
                    System.out.println("Name        : " + emp.getName());
                    System.out.println("Role        : " + emp.getRole());
                    break;

                case 2:
                    leaveService.viewLeaveBalance(emp.getEmployeeId());
                    break;

                case 3:
                    leaveService.applyLeave(emp.getEmployeeId());
                    break;

                case 4:
                    leaveService.viewLeaveStatus(emp.getEmployeeId());
                    break;
                case 5:
                    new NotificationDao().viewNotifications(emp.getEmployeeId());
                    break;
                
                case 6:
                    new PerformanceService().submitReview(emp.getEmployeeId());
                    break;
                case 7:
                    new GoalService().addGoal(emp.getEmployeeId());
                    break;
                case 8:
                    new GoalService().viewGoals(emp.getEmployeeId());
                    break;
                case 9:
                    System.out.println("Logout successful!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
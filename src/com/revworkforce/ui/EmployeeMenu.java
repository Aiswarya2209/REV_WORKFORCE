package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.*;

public class EmployeeMenu {

    public static void show(Employee emp) {

        Scanner sc = new Scanner(System.in);

        LeaveService leaveService = new LeaveService();
        GoalService goalService = new GoalService();
        NotificationService notificationService = new NotificationService();
        PerformanceService performanceService = new PerformanceService();
        EmployeeService employeeService = new EmployeeService();
        HolidayService holidayService = new HolidayService();

        int choice = 0;

        do {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1. View Profile");
            System.out.println("2. View My Leaves");
            System.out.println("3. Apply Leave");
            System.out.println("4. View Leave Status");
            System.out.println("5. View My Leave Balance");
            System.out.println("6. Update Profile");
            System.out.println("7. Add Goal");
            System.out.println("8. View My Goals");
            System.out.println("9. Update Goal Progress");
            System.out.println("10. View Reporting Manager");
            System.out.println("11. View Notifications");
            System.out.println("12. View Holiday Calendar");
            System.out.println("13. Change Password");
            System.out.println("14. Submit Performance Review");
            System.out.println("15. View My Performance Review");
            System.out.println("16. Logout");
            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number only.");
                continue;
            }

            switch (choice) {

                case 1:
                    employeeService.viewProfile(emp.getEmployeeId());
                    break;

                case 2:
                    leaveService.viewMyLeaves(emp.getEmployeeId());
                    break;

                case 3:
                    leaveService.applyLeave(emp.getEmployeeId());
                    break;

                case 4:
                    leaveService.viewLeaveStatus(emp.getEmployeeId());
                    break;

                case 5:
                    leaveService.viewLeaveBalance(emp.getEmployeeId());
                    break;

                case 6:
                    employeeService.updateProfile(emp.getEmployeeId(), sc);
                    break;

                case 7:
                    goalService.addGoal(emp.getEmployeeId(), sc);
                    break;

                case 8:
                    goalService.viewMyGoals(emp.getEmployeeId());
                    break;

                case 9:
                    goalService.updateGoalProgress(emp.getEmployeeId(), sc);
                    break;

                case 10:
                    employeeService.viewReportingManager(emp.getEmployeeId());
                    break;

                case 11:
                    notificationService.viewNotifications(emp.getEmployeeId());
                    break;

                case 12:
                    holidayService.viewHolidays();
                    break;

                case 13:
                    employeeService.changePassword(emp.getEmployeeId(), sc);
                    break;

                case 14:
                    performanceService.submitReview(emp.getEmployeeId());
                    break;

                case 15:
                    employeeService.viewMyPerformanceReview(emp.getEmployeeId());
                    break;

                case 16:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1 and 16.");
            }

        } while (choice != 16);
    }
}
package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.GoalService;
import com.revworkforce.service.ManagerService;

public class ManagerMenu {

    public static void show(Employee manager) {

        Scanner sc = new Scanner(System.in);
        ManagerService service = new ManagerService();
        GoalService goalService = new GoalService();

        int choice;

        do {
            System.out.println("\n===== Manager Menu =====");
            System.out.println("1. View Team Leave Requests");
            System.out.println("2. Approve / Reject Leave");
            System.out.println("3. View Team Performance Reviews");
            System.out.println("4. Review Employee Performance");
            System.out.println("5. Update Team Goal Status");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    service.viewTeamLeaves(manager.getEmployeeId());
                    break;

                case 2:
                    service.approveRejectLeave(manager.getEmployeeId());
                    break;

                case 3:
                    service.viewTeamReviews(manager.getEmployeeId());
                    break;

                case 4:
                    service.reviewEmployee();
                    break;

                case 5:
                    goalService.updateGoalStatus();
                    break;

                case 6:
                    System.out.println("Logout successful!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 6);
    }
}
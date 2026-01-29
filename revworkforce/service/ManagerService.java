package com.revworkforce.service;

import java.util.Scanner;
import com.revworkforce.dao.ManagerDao;
import com.revworkforce.dao.PerformanceDao;

public class ManagerService {

    private ManagerDao dao = new ManagerDao();
    private PerformanceDao perfDao = new PerformanceDao();
    private Scanner sc = new Scanner(System.in);

    public void viewTeamLeaves(int managerId) {
        dao.getTeamLeaveRequests(managerId);
    }

    public void approveRejectLeave(int managerId) {
        System.out.print("Enter Leave ID: ");
        int leaveId = sc.nextInt();

        System.out.print("Approve or Reject (A/R): ");
        String status = sc.next();

        dao.updateLeaveStatus(leaveId, status.equalsIgnoreCase("A") ? "APPROVED" : "REJECTED");
    }

    // View team performance reviews
    public void viewTeamReviews(int managerId) {
        perfDao.viewTeamReviews(managerId);
    }

    // Review an employee performance
    public void reviewEmployee() {
        System.out.print("Enter Review ID: ");
        int reviewId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Manager Feedback: ");
        String feedback = sc.nextLine();

        System.out.print("Enter Manager Rating (1-5): ");
        int rating = sc.nextInt();

        perfDao.reviewEmployee(reviewId, feedback, rating);
    }
}
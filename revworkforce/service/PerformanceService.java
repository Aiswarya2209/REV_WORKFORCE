package com.revworkforce.service;

import java.util.Scanner;
import com.revworkforce.dao.PerformanceDao;

public class PerformanceService {

    private PerformanceDao dao = new PerformanceDao();
    private Scanner sc = new Scanner(System.in);

    // Employee
    public void submitReview(int empId) {
        System.out.print("Year: ");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.print("Achievements: ");
        String ach = sc.nextLine();
        System.out.print("Improvements: ");
        String imp = sc.nextLine();
        System.out.print("Self Rating (1-5): ");
        int rating = sc.nextInt();

        dao.submitReview(empId, year, ach, imp, rating);
    }

    // Manager
    public void viewTeamReviews(int managerId) {
        dao.viewTeamReviews(managerId);
    }

    public void reviewEmployee() {
        System.out.print("Enter Review ID: ");
        int reviewId = sc.nextInt();
        sc.nextLine();
        System.out.print("Manager Feedback: ");
        String fb = sc.nextLine();
        System.out.print("Manager Rating (1-5): ");
        int rating = sc.nextInt();

        dao.reviewEmployee(reviewId, fb, rating);
    }
}

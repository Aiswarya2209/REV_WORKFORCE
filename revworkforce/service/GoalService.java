package com.revworkforce.service;

import java.util.Scanner;
import com.revworkforce.dao.GoalDao;

public class GoalService {

    private GoalDao dao = new GoalDao();
    private Scanner sc = new Scanner(System.in);

    // Employee
    public void addGoal(int empId) {
        System.out.print("Goal Description: ");
        sc.nextLine();
        String desc = sc.nextLine();
        System.out.print("Deadline (YYYY-MM-DD): ");
        String date = sc.next();
        System.out.print("Priority (HIGH/MEDIUM/LOW): ");
        String priority = sc.next();
        System.out.print("Success Metrics: ");
        sc.nextLine();
        String metrics = sc.nextLine();

        dao.addGoal(empId, desc, date, priority, metrics);
    }

    public void viewGoals(int empId) {
        dao.viewGoals(empId);
    }

    // Manager
    public void updateGoal() {
        System.out.print("Enter Goal ID: ");
        int goalId = sc.nextInt();
        System.out.print("Update Status (IN_PROGRESS / COMPLETED): ");
        String status = sc.next();

        dao.updateGoalStatus(goalId, status);
    }
}
package com.revworkforce.service;

import java.util.Scanner;

public class GoalService {

    public void addGoal(int empId, Scanner sc) {
        System.out.print("Enter Goal Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Goal Description: ");
        String desc = sc.nextLine();

        System.out.println("Goal added for employee " + empId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + desc);
    }

    public void viewMyGoals(int empId) {
        System.out.println("Goals for employee " + empId);
        System.out.println("1. Complete Project A");
        System.out.println("2. Improve Coding Skills");
    }

    public void updateGoalProgress(int empId, Scanner sc) {
        System.out.print("Enter Goal ID: ");
        int goalId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Progress: ");
        String progress = sc.nextLine();

        System.out.println("Goal " + goalId + " updated: " + progress);
    }

    
    public void updateGoalStatus() {
        System.out.println("Manager updated team goal status.");
    }
}
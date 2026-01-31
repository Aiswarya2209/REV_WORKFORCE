package com.revworkforce.service;

import java.util.Scanner;
import java.sql.Date;
import java.util.Calendar;

import com.revworkforce.dao.LeaveDao;

public class LeaveService {

    private LeaveDao dao = new LeaveDao();
    private Scanner sc = new Scanner(System.in);

    
    public void applyLeave(int empId) {
        try {
            System.out.print("Enter Leave Type ID (1-CL, 2-SL, 3-PL): ");
            int leaveTypeId = sc.nextInt();
            sc.nextLine();

            System.out.print("From Date (YYYY-MM-DD): ");
            Date fromDate = Date.valueOf(sc.nextLine());

            System.out.print("To Date (YYYY-MM-DD): ");
            Date toDate = Date.valueOf(sc.nextLine());

            
            if (toDate.before(fromDate)) {
                System.out.println("To Date cannot be before From Date.");
                return;
            }

            
            Date today = new Date(Calendar.getInstance().getTimeInMillis());
            if (fromDate.before(today)) {
                System.out.println("Leave cannot be applied for past dates.");
                return;
            }

            
            long diffMillis = toDate.getTime() - fromDate.getTime();
            long requestedDays = (diffMillis / (1000 * 60 * 60 * 24)) + 1;

            int remainingLeaves = dao.getRemainingLeaves(empId, leaveTypeId);

            if (requestedDays > remainingLeaves) {
                System.out.println(
                    "You have only " + remainingLeaves +
                    " leaves remaining. You requested " + requestedDays + " days."
                );
                return;
            }

            System.out.print("Reason: ");
            String reason = sc.nextLine();

            System.out.print("Confirm Apply Leave (YES/NO): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("YES")) {
                System.out.println("Leave Application Cancelled.");
                return;
            }

            boolean success = dao.applyLeave(empId, leaveTypeId, fromDate, toDate, reason);

            if (success) {
                System.out.println("Leave Applied Successfully!");
            } else {
                System.out.println("Failed to apply leave.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewMyLeaves(int empId) {
        dao.viewMyLeaves(empId);
    }

    public void viewLeaveStatus(int empId) {
        dao.viewLeaveStatus(empId);
    }

    public void viewLeaveBalance(int empId) {
        dao.viewLeaveBalance(empId);
    }
}

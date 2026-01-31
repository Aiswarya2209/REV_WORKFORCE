package com.revworkforce.service;

import java.util.Scanner;
import com.revworkforce.dao.LeaveDao;

public class LeaveService {

	private LeaveDao dao = new LeaveDao();
	private Scanner sc = new Scanner(System.in);

	public void viewLeaveBalance(int empId) {
		dao.viewLeaveBalance(empId);
	}

	public void applyLeave(int empId) {

		System.out.print("Enter Leave Type ID (1-CL, 2-SL, 3-PL): ");
		int leaveTypeId = sc.nextInt();

		System.out.print("From Date (YYYY-MM-DD): ");
		String fromDate = sc.next();

		System.out.print("To Date (YYYY-MM-DD): ");
		String toDate = sc.next();

		System.out.print("Reason: ");
		sc.nextLine();
		String reason = sc.nextLine();

		System.out
				.print("Are you sure you want to apply this leave? (YES/NO): ");
		String confirm = sc.next();

		if (confirm.equalsIgnoreCase("YES")) {
			dao.applyLeave(empId, leaveTypeId, fromDate, toDate, reason);
			//System.out.println("Leave Applied Successfully!");
		} else {
			System.out.println("Leave Application Cancelled.");
		}
	}

	public void viewLeaveStatus(int empId) {
		dao.viewLeaveStatus(empId);
	}
}

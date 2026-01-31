package com.revworkforce.service;

import java.util.List;
import java.util.Scanner;

import com.revworkforce.dao.EmployeeDao;
import com.revworkforce.model.Employee;
import com.revworkforce.model.Holiday;
import com.revworkforce.model.PerformanceReview;

public class EmployeeService {

    private EmployeeDao dao = new EmployeeDao();

    
    public void viewProfile(int empId) {
        Employee emp = dao.getEmployeeById(empId);
        if (emp != null) {
            System.out.println("\n--- My Profile ---");
            System.out.printf("ID: %d, Name: %s, Email: %s, Phone: %s, Role: %s, Dept: %s%n",
                    emp.getEmployeeId(), emp.getName(), emp.getEmail(), emp.getPhone(), emp.getRole(), emp.getDepartment());
        } else System.out.println("Employee not found.");
    }

   
    public void updateProfile(int empId, Scanner sc) {
        sc.nextLine(); 
        System.out.print("Enter New Email: ");
        String email = sc.nextLine();
        System.out.print("Enter New Phone: ");
        String phone = sc.nextLine();

        boolean updated = dao.updateProfile(empId, email, phone);
        System.out.println(updated ? "Profile Updated Successfully!" : "Profile Update Failed.");
    }

    
    public void viewReportingManager(int empId) {
        Employee mgr = dao.getReportingManager(empId);
        if (mgr != null) {
            System.out.printf("\n--- Manager ---\nID: %d, Name: %s, Email: %s, Phone: %s%n",
                    mgr.getEmployeeId(), mgr.getName(), mgr.getEmail(), mgr.getPhone());
        } else System.out.println("No Reporting Manager Assigned.");
    }

   
    public void viewHolidayCalendar() {
        List<Holiday> holidays = dao.getHolidayCalendar();
        System.out.println("\n--- Holiday Calendar ---");
        for (Holiday h : holidays) System.out.printf("%s - %s%n", h.getDate(), h.getName());
    }

    
    public void changePassword(int empId, Scanner sc) {
        //sc.nextLine();
        System.out.print("Enter Old Password: "); String oldPwd = sc.nextLine();
        System.out.print("Enter New Password: "); String newPwd = sc.nextLine();
        boolean changed = dao.changePassword(empId, oldPwd, newPwd);
        System.out.println(changed ? "Password Changed Successfully!" : "Old Password is Incorrect.");
    }

    
        private EmployeeDao employeeDao = new EmployeeDao();

        public void viewMyPerformanceReview(int empId) {

            List<PerformanceReview> reviews =
                    employeeDao.getMyPerformanceReview(empId);

            System.out.println("\n--- Performance Reviews ---");

            if (reviews == null || reviews.isEmpty()) {
                System.out.println("No reviews found.");
                return;
            }

            for (PerformanceReview pr : reviews) {
                System.out.println("Review Year      : " + pr.getReviewYear());
                System.out.println("Self Rating      : " + pr.getSelfRating());
                System.out.println("Manager Rating   : " + pr.getManagerRating());
                System.out.println("Manager Feedback : " + pr.getManagerFeedback());
                System.out.println("----------------------------");
            }
        }
    }
    

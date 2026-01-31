package com.revworkforcetest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.revworkforce.dao.LeaveDao;

public class LeaveDaoTest {

    private LeaveDao leaveDao;

    @Before
    public void setUp() {
        leaveDao = new LeaveDao();
    }

    
    @Test
    public void testViewMyLeaves() {
        int empId = 101; 
        leaveDao.viewMyLeaves(empId);
        System.out.println("View My Leaves Test Passed");
    }

    
    @Test
    public void testApplyLeave_Success() {
        int empId = 101; 
        int leaveTypeId = 1; // CL
        Date fromDate = Date.valueOf("2026-02-01");
        Date toDate = Date.valueOf("2026-02-03");
        String reason = "Family function";

        boolean result = leaveDao.applyLeave(empId, leaveTypeId, fromDate, toDate, reason);
        assertTrue("Leave should be applied successfully", result);
        System.out.println("Apply Leave Test Passed");
    }

    @Test
    public void testApplyLeave_InvalidDates() {
        int empId = 101;
        int leaveTypeId = 1;
        Date fromDate = Date.valueOf("2026-02-10");
        Date toDate = Date.valueOf("2026-02-05"); 
        String reason = "Test invalid date";

        boolean result = leaveDao.applyLeave(empId, leaveTypeId, fromDate, toDate, reason);
        assertFalse("Leave should fail due to invalid dates", result);
        System.out.println("Apply Leave Invalid Dates Test Passed");
    }

    
    @Test
    public void testViewLeaveStatus() {
        int empId = 101;
        leaveDao.viewLeaveStatus(empId);
        System.out.println("View Leave Status Test Passed");
    }
}

    

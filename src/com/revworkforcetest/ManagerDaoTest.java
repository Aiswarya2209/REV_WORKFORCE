package com.revworkforcetest;

import org.junit.Before;
import org.junit.Test;

import com.revworkforce.dao.ManagerDao;

public class ManagerDaoTest {

    private ManagerDao managerDao;

    @Before
    public void setUp() {
        managerDao = new ManagerDao();
    }

    
    @Test
    public void testGetTeamLeaveRequests() {
        int managerId = 102; 
        managerDao.getTeamLeaveRequests(managerId);
        System.out.println("Get Team Leave Requests Test Passed");
    }

    
    @Test
    public void testApproveLeave() {
        int leaveId = 1; 
        String status = "APPROVED";
        managerDao.updateLeaveStatus(leaveId, status);
        System.out.println("Approve Leave Test Passed");
    }

   
    @Test
    public void testRejectLeave() {
        int leaveId = 2; 
        String status = "REJECTED";
        managerDao.updateLeaveStatus(leaveId, status);
        System.out.println("Reject Leave Test Passed");
    }
}

package com.revworkforcetest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.revworkforce.model.LeaveRequest;

public class LeaveRequestTest {

    @Test
    public void testSettersAndGetters() {

        LeaveRequest leave = new LeaveRequest();

        Date fromDate = Date.valueOf("2026-02-01");
        Date toDate = Date.valueOf("2026-02-05");

        leave.setLeaveId(101);
        leave.setEmployeeId(1001);
        leave.setLeaveTypeId(2);
        leave.setFromDate(fromDate);
        leave.setToDate(toDate);
        leave.setReason("Medical Leave");
        leave.setStatus("PENDING");

        assertEquals(101, leave.getLeaveId());
        assertEquals(1001, leave.getEmployeeId());
        assertEquals(2, leave.getLeaveTypeId());
        assertEquals(fromDate, leave.getFromDate());
        assertEquals(toDate, leave.getToDate());
        assertEquals("Medical Leave", leave.getReason());
        assertEquals("PENDING", leave.getStatus());
    }

    @Test
    public void testDefaultValues() {

        LeaveRequest leave = new LeaveRequest();

        assertEquals(0, leave.getLeaveId());
        assertEquals(0, leave.getEmployeeId());
        assertEquals(0, leave.getLeaveTypeId());
        assertNull(leave.getFromDate());
        assertNull(leave.getToDate());
        assertNull(leave.getReason());
        assertNull(leave.getStatus());
    }
}

package com.revworkforcetest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revworkforce.service.AdminService;

public class AdminServiceTest {

    private AdminService adminService;

    @Before
    public void setUp() {
        adminService = new AdminService();
    }

    @Test
    public void testAddHoliday_Success() {
        String name = "Republic Day";
        String date = "2026-01-26"; 
        String description = "National Holiday";

        boolean result = adminService.addHoliday(name, date, description);
        assertTrue("Holiday should be added successfully", result);
        System.out.println("Add Holiday Test Passed");
    }

    @Test
    public void testAddHoliday_InvalidDate() {
        String name = "Invalid Holiday";
        String date = "2026-13-01"; 
        String description = "Test";

        boolean result = adminService.addHoliday(name, date, description);
        assertFalse("Holiday should fail with invalid date", result);
        System.out.println("Add Holiday Invalid Date Test Passed");
    }
}

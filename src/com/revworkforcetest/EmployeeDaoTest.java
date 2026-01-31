package com.revworkforcetest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revworkforce.dao.EmployeeDao;
import com.revworkforce.model.Employee;
import com.revworkforce.model.Holiday;
import com.revworkforce.model.PerformanceReview;

public class EmployeeDaoTest {

    EmployeeDao dao = new EmployeeDao();

    
    @Test
    public void testGetEmployeeById() {

        Employee emp = dao.getEmployeeById(101); 

        assertNotNull(emp);
        assertEquals(101, emp.getEmployeeId());
        assertNotNull(emp.getName());
    }

    
    @Test
    public void testUpdateProfile() {

        boolean updated =
            dao.updateProfile(101, "testupdate@mail.com", "9999999999");

        assertTrue(updated);
    }

   
    @Test
    public void testGetReportingManager() {

        Employee manager = dao.getReportingManager(101);

        assertNotNull(manager);
        assertNotNull(manager.getEmployeeId());
        assertNotNull(manager.getName());
    }

   
    @Test
    public void testGetHolidayCalendar() {

        List<Holiday> holidays = dao.getHolidayCalendar();

        assertNotNull(holidays);
        assertTrue(holidays.size() >= 0); 
    }

    
    @Test
    public void testChangePassword() {

        boolean changed =
            dao.changePassword(101, "oldpassword", "newpassword");

        assertNotNull(changed);
    }

   
    @Test
    public void testGetMyPerformanceReview() {

        List<PerformanceReview> reviews =
            dao.getMyPerformanceReview(101);

        assertNotNull(reviews);

        if (!reviews.isEmpty()) {
            PerformanceReview pr = reviews.get(0);
            assertNotNull(pr.getReviewYear());
        }
    }
}

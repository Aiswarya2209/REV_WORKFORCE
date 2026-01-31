package com.revworkforcetest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revworkforce.model.Employee;
import com.revworkforce.service.AuthService;

public class AuthServiceTest {

    private AuthService authService;

    @Before
    public void setUp() {
        authService = new AuthService();
    }

    @Test
    public void testAuthenticateSuccess() {
        int empId = 101; 
        String password = "Rev@123"; 

        Employee emp = authService.authenticate(empId, password);

        assertNotNull("Employee should be returned for valid credentials", emp);
        assertEquals("Employee ID should match", empId, emp.getEmployeeId());
        System.out.println("Authenticate Success Test Passed");
    }

    @Test
    public void testAuthenticateWrongPassword() {
        int empId = 101; 
        String wrongPassword = "wrongpass";

        Employee emp = authService.authenticate(empId, wrongPassword);

        assertNull("Employee should be null for wrong password", emp);
        System.out.println("Authenticate Failure Test Passed for wrong password");
    }

    @Test
    public void testAuthenticateEmptyPassword() {
        int empId = 101;
        String emptyPassword = "";

        Employee emp = authService.authenticate(empId, emptyPassword);

        assertNull("Employee should be null for empty password", emp);
        System.out.println("Authenticate Failure Test Passed for empty password");
    }

    @Test
    public void testAuthenticateNullPassword() {
        int empId = 101;
        String nullPassword = null;

        Employee emp = authService.authenticate(empId, nullPassword);

        assertNull("Employee should be null for null password", emp);
        System.out.println("Authenticate Failure Test Passed for null password");
    }
}

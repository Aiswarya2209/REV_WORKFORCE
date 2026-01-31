package com.revworkforcetest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revworkforce.dao.LoginDAO;
import com.revworkforce.model.Employee;

public class LoginDAOTest {

    private LoginDAO loginDAO;

    @Before
    public void setUp() {
        loginDAO = new LoginDAO(); 
    }

    @Test
    public void testLoginSuccess() {
        
        int empId = 101;
        String password = "Rev@123";

        Employee emp = loginDAO.Login(empId, password);
        assertNotNull("Employee should be returned", emp);
        assertEquals("Employee ID should match", empId, emp.getEmployeeId());
        System.out.println("Login Success Test Passed: " + emp.getName() + " - " + emp.getRole());
    }

    @Test
    public void testLoginFailureWrongPassword() {
        int empId = 101;
        String wrongPassword = "wrongpass";

        Employee emp = loginDAO.Login(empId, wrongPassword);
        assertNull("Employee should be null for wrong password", emp);
        System.out.println("Login Failure Test Passed for wrong password");
    }

    @Test
    public void testLoginFailureInactiveEmployee() {
        int empId = 102; 
        String password = "anyPassword";

        Employee emp = loginDAO.Login(empId, password);
        assertNull("Employee should be null for inactive employee", emp);
        System.out.println("Login Failure Test Passed for inactive employee");
    }
}

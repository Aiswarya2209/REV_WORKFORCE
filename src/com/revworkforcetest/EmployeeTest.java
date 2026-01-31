package com.revworkforcetest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revworkforce.model.Employee;

public class EmployeeTest {

    @Test
    public void testEmployeeGettersAndSetters() {

        Employee emp = new Employee();

        emp.setEmployeeId(101);
        emp.setName("John");
        emp.setRole("Developer");
        emp.setEmail("john@test.com");
        emp.setPhone("9876543210");
        emp.setDepartment("IT");

        assertEquals(101, emp.getEmployeeId());
        assertEquals("John", emp.getName());
        assertEquals("Developer", emp.getRole());
        assertEquals("john@test.com", emp.getEmail());
        assertEquals("9876543210", emp.getPhone());
        assertEquals("IT", emp.getDepartment());
    }

    @Test
    public void testEmployeeDefaultValues() {

        Employee emp = new Employee();

        assertEquals(0, emp.getEmployeeId());
        assertNull(emp.getName());
        assertNull(emp.getRole());
        assertNull(emp.getEmail());
        assertNull(emp.getPhone());
        assertNull(emp.getDepartment());
    }
}

package com.revworkforcetest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
//import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revworkforce.service.ManagerService;

public class ManagerServiceTest {

    private ManagerService service;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        service = new ManagerService();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    
    @Test
    public void testViewTeamLeaves() {
        int managerId = 101;
        service.viewTeamLeaves(managerId);
        
    }

    
    @Test
    public void testApproveLeave() {
        String input = "201\nA\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        service.approveRejectLeave(101);
    }

    
    @Test
    public void testRejectLeave() {
        String input = "202\nR\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        service.approveRejectLeave(101);
    }

    
    @Test
    public void testViewTeamReviews() {
        int managerId = 101;
        service.viewTeamReviews(managerId);
       
    }

    
    @Test
    public void testReviewEmployee() {
        String input = "301\nExcellent Work\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        service.reviewEmployee();
    }
}

package com.revworkforcetest;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revworkforce.service.GoalService;

public class GoalServiceTest {

    private GoalService service;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        service = new GoalService();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddGoal() {

        String input = "Goal Title\nGoal Description\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        service.addGoal(101, sc);

        String output = outContent.toString();
        assertTrue(output.contains("Goal added for employee 101"));
        assertTrue(output.contains("Goal Title"));
        assertTrue(output.contains("Goal Description"));
    }

    @Test
    public void testViewMyGoals() {

        service.viewMyGoals(101);

        String output = outContent.toString();
        assertTrue(output.contains("Goals for employee 101"));
        assertTrue(output.contains("Complete Project A"));
        assertTrue(output.contains("Improve Coding Skills"));
    }

    @Test
    public void testUpdateGoalProgress() {

        String input = "1\n50% completed\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        service.updateGoalProgress(101, sc);

        String output = outContent.toString();
        assertTrue(output.contains("Goal 1 updated"));
        assertTrue(output.contains("50% completed"));
    }

    @Test
    public void testUpdateGoalStatus() {

        service.updateGoalStatus();

        String output = outContent.toString();
        assertTrue(output.contains("Manager updated team goal status"));
    }
}

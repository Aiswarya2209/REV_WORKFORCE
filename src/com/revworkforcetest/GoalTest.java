package com.revworkforcetest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.revworkforce.model.Goal;

public class GoalTest {

    @Test
    public void testGoalGettersAndSetters() {

        Goal goal = new Goal();

        Date deadline = Date.valueOf("2026-12-31");

        goal.setGoalId(1);
        goal.setEmployeeId(101);
        goal.setDescription("Complete Spring Boot module");
        goal.setDeadline(deadline);
        goal.setPriority("HIGH");
        goal.setSuccessMetrics("All features delivered");
        goal.setProgressStatus("IN_PROGRESS");

        assertEquals(1, goal.getGoalId());
        assertEquals(101, goal.getEmployeeId());
        assertEquals("Complete Spring Boot module", goal.getDescription());
        assertEquals(deadline, goal.getDeadline());
        assertEquals("HIGH", goal.getPriority());
        assertEquals("All features delivered", goal.getSuccessMetrics());
        assertEquals("IN_PROGRESS", goal.getProgressStatus());
    }

    @Test
    public void testGoalDefaultValues() {

        Goal goal = new Goal();

        assertEquals(0, goal.getGoalId());
        assertEquals(0, goal.getEmployeeId());
        assertNull(goal.getDescription());
        assertNull(goal.getDeadline());
        assertNull(goal.getPriority());
        assertNull(goal.getSuccessMetrics());
        assertNull(goal.getProgressStatus());
    }
}

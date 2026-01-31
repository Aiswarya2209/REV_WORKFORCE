package com.revworkforcetest;

//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revworkforce.dao.PerformanceDao;

public class PerformanceDaoTest {

    private PerformanceDao perfDao;

    @Before
    public void setUp() {
        perfDao = new PerformanceDao();
    }

    
    @Test
    public void testSubmitReview_Success() {
        int empId = 101;      
        int year = 2026;
        String achievements = "Completed Project X";
        String improvements = "Improve communication";
        int selfRating = 4;

        perfDao.submitReview(empId, year, achievements, improvements, selfRating);
        
        System.out.println("Submit Review Test Passed");
    }

    
    @Test
    public void testViewMyReview() {
        int empId = 101; 
        perfDao.viewMyReview(empId);
        System.out.println("View My Review Test Passed");
    }

    
    @Test
    public void testViewTeamReviews() {
        int managerId = 102; 
        perfDao.viewTeamReviews(managerId);
        System.out.println("View Team Reviews Test Passed");
    }

    
    @Test
    public void testReviewEmployee() {
        int reviewId = 1; 
        String feedback = "Excellent work!";
        int rating = 5;

        perfDao.reviewEmployee(reviewId, feedback, rating);
        System.out.println("Review Employee Test Passed");
    }
}

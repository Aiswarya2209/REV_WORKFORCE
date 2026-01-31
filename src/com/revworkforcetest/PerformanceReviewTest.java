package com.revworkforcetest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revworkforce.model.PerformanceReview;

public class PerformanceReviewTest {

    @Test
    public void testSettersAndGetters() {

        PerformanceReview review = new PerformanceReview();

        review.setReviewYear(2025);
        review.setSelfRating(4);
        review.setManagerRating(5);
        review.setManagerFeedback("Excellent performance");

        assertEquals(2025, review.getReviewYear());
        assertEquals(4, review.getSelfRating());
        assertEquals(5, review.getManagerRating());
        assertEquals("Excellent performance", review.getManagerFeedback());
    }

    @Test
    public void testDefaultValues() {

        PerformanceReview review = new PerformanceReview();

        assertEquals(0, review.getReviewYear());
        assertEquals(0, review.getSelfRating());
        assertEquals(0, review.getManagerRating());
        assertNull(review.getManagerFeedback());
    }
}

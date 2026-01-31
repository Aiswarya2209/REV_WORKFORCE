package com.revworkforce.model;

public class PerformanceReview {

    private int reviewYear;
    private int selfRating;
    private int managerRating;
    private String managerFeedback;

    public int getReviewYear() {
        return reviewYear;
    }

    public void setReviewYear(int reviewYear) {
        this.reviewYear = reviewYear;
    }

    public int getSelfRating() {
        return selfRating;
    }

    public void setSelfRating(int selfRating) {
        this.selfRating = selfRating;
    }

    public int getManagerRating() {
        return managerRating;
    }

    public void setManagerRating(int managerRating) {
        this.managerRating = managerRating;
    }

    public String getManagerFeedback() {
        return managerFeedback;
    }

    public void setManagerFeedback(String managerFeedback) {
        this.managerFeedback = managerFeedback;
    }
}
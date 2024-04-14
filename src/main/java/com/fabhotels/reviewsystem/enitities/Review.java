package com.fabhotels.reviewsystem.enitities;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewee_id")
    private User reviewee;

    private String skill;
    private Integer score;

    public Review() {
    }

    public Review(Integer reviewId, User reviewer, User reviewee, String skill, Integer score) {
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
        this.skill = skill;
        this.score = score;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getReviewee() {
        return reviewee;
    }

    public void setReviewee(User reviewee) {
        this.reviewee = reviewee;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

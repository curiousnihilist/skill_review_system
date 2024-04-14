package com.fabhotels.reviewsystem.dto;

import com.fabhotels.reviewsystem.enitities.Skill;

public class ReviewRequest {

    private Integer id;
    private UserDto reviewer;

    private UserDto reviewee;

    private String skill;

    private Integer score;

    public ReviewRequest() {
    }

    public ReviewRequest(UserDto reviewer, UserDto reviewee, String skill, Integer score) {
        this.reviewer = reviewer;
        this.reviewee = reviewee;
        this.skill = skill;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserDto reviewer) {
        this.reviewer = reviewer;
    }

    public UserDto getReviewee() {
        return reviewee;
    }

    public void setReviewee(UserDto reviewee) {
        this.reviewee = reviewee;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

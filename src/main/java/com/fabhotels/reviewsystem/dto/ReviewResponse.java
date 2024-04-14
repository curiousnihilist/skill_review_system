package com.fabhotels.reviewsystem.dto;

import java.util.ArrayList;
import java.util.List;

public class ReviewResponse {

    private int id;

    private UserDto reviewer;

    private String skill;

    private Integer score;

    private Double relevanceScore;

    private List<String> reasons;

    public ReviewResponse(){
        this.relevanceScore = 0.0;
        this.reasons = new ArrayList<>();
    }

    public ReviewResponse(int id, UserDto reviewer, String skill, Integer score) {
        this();
        this.id = id;
        this.reviewer = reviewer;
        this.skill = skill;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getRelevanceScore() {
        return relevanceScore;
    }

    public void setRelevanceScore(Double relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

    public void addWeightedScore(Double weightedScore) {
        this.relevanceScore += weightedScore;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }

    public void addReason(String reason) {
        this.reasons.add(reason);
    }

    public UserDto getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserDto reviewer) {
        this.reviewer = reviewer;
    }
}

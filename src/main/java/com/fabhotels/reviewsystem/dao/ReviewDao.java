package com.fabhotels.reviewsystem.dao;

import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.enitities.Review;
import com.fabhotels.reviewsystem.enitities.SkillGroup;

import java.util.List;
import java.util.Map;

public interface ReviewDao {

    Integer ifReviewExistsForGivenSkill(String skillName, Integer revieweeId);

    void updateReviewIfExists(ReviewRequest reviewRequest, Integer id);

    List<Map<String,Object>> fetchRequestByRevieweeId(Integer userId);

    SkillGroup fetchSkillGroupBasedOnSkills(List<String> skills);

}

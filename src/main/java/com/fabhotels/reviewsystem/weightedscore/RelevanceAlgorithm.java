package com.fabhotels.reviewsystem.weightedscore;

import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.dto.ReviewResponse;
import com.fabhotels.reviewsystem.enitities.Review;

import java.util.List;

public interface RelevanceAlgorithm {

    void calculateCoworkerRelevance(ReviewRequest review, ReviewResponse response);

    void calculateExperienceRelevance(ReviewRequest review, ReviewResponse response);

    void calculateSkillClosenessRelevance(ReviewRequest review, ReviewResponse response);





}

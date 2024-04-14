package com.fabhotels.reviewsystem.utils;

import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.dto.ReviewResponse;
import com.fabhotels.reviewsystem.dto.UserDto;
import com.fabhotels.reviewsystem.enitities.Review;
import org.apache.commons.lang3.StringUtils;

import static com.fabhotels.reviewsystem.constants.ReviewConstants.*;

public class ReviewUtils {

    private ReviewUtils(){
    }

    public static String validateReviewRequestValid(ReviewRequest request){
        if(request.getReviewee().getId()<0){
            return INVALID_REVIEWEE;
        }
        if(request.getReviewer().getId()<0){
            return INVALID_REVIEWER;
        }
        if(request.getSkill()==null){
            return INVALID_SKILL;
        }
        if(request.getScore()<0 && request.getScore()>5) {
            return INVALID_SCORE;
        }
        return StringUtils.EMPTY;
    }

    public static ReviewResponse convertReviewObjectToReviewResponseObject(ReviewRequest review){
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setReviewer(new UserDto(review.getReviewer().getId(), review.getReviewer().getName()));
        response.setScore(review.getScore());
        response.setSkill(review.getSkill());
        return  response;
    }
}

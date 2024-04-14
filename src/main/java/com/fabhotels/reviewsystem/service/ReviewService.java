package com.fabhotels.reviewsystem.service;


import com.fabhotels.reviewsystem.dto.ResponseObj;
import com.fabhotels.reviewsystem.dto.ReviewRequest;

public interface ReviewService {

    ResponseObj fetchReviewsByUserId(Integer id);

    ResponseObj addReview(ReviewRequest review);
}

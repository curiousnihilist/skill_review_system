package com.fabhotels.reviewsystem.controller;

import com.fabhotels.reviewsystem.dto.ResponseObj;
import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reviewsystem")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService service){
        this.reviewService = service;
    }

    @PostMapping(value = "/review")
    public ResponseObj addReview(@RequestBody ReviewRequest reviewRequest){
        return reviewService.addReview(reviewRequest);
    }

    @GetMapping(value = "/review/users/{id}")
    public ResponseObj addReview(@PathVariable(value = "id") String id){
        return reviewService.fetchReviewsByUserId(Integer.valueOf(id));
    }
}

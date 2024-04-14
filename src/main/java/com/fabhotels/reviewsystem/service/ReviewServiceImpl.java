package com.fabhotels.reviewsystem.service;

import com.fabhotels.reviewsystem.constants.ReviewConstants;
import com.fabhotels.reviewsystem.dao.ReviewDao;
import com.fabhotels.reviewsystem.dao.ReviewRepository;
import com.fabhotels.reviewsystem.dao.SkillDao;
import com.fabhotels.reviewsystem.dao.UserRepository;
import com.fabhotels.reviewsystem.dto.ResponseObj;
import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.dto.ReviewResponse;
import com.fabhotels.reviewsystem.dto.UserDto;
import com.fabhotels.reviewsystem.enitities.Review;
import com.fabhotels.reviewsystem.enitities.Skill;
import com.fabhotels.reviewsystem.enitities.SkillGroup;
import com.fabhotels.reviewsystem.enitities.User;
import com.fabhotels.reviewsystem.utils.CommonUtils;
import com.fabhotels.reviewsystem.utils.ReviewUtils;
import com.fabhotels.reviewsystem.weightedscore.RelevanceAlgorithmHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fabhotels.reviewsystem.constants.ReviewConstants.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private SkillDao skillRepository;
    private ReviewDao reviewDao;
    private UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,SkillDao skillRepository,ReviewDao reviewDao,
                             UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.skillRepository = skillRepository;
        this.reviewDao = reviewDao;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseObj fetchReviewsByUserId(Integer id) {
        if(id.intValue()<0){
            return CommonUtils.getResponseObjForFailure("Invalid user id!");
        }
        try{
            List<ReviewRequest> reviews = new ArrayList<>();
            List<Map<String,Object>> result = reviewDao.fetchRequestByRevieweeId(id);
            for (Map<String, Object> stringObjectMap : result) {
                Integer reviewerId = (Integer) stringObjectMap.get("reviewer_id");
                Integer revieweeId = (Integer) stringObjectMap.get("reviewee_id");
                Optional<User> reviewer = userRepository.findById(reviewerId);
                Optional<User> reviewee = userRepository.findById(revieweeId);

                ReviewRequest review = new ReviewRequest();
                review.setId((Integer) stringObjectMap.get("reviewer_id"));
                review.setReviewee(convertUserEntityToDto(reviewee.get()));
                review.setReviewer(convertUserEntityToDto(reviewer.get()));
                addRelevantSkillGroup(review.getReviewer());
                addRelevantSkillGroup(review.getReviewee());
                review.setSkill((String) stringObjectMap.get("skill_name"));
                review.setScore((Integer) stringObjectMap.get("score"));
                reviews.add(review);
            }
            RelevanceAlgorithmHelper relevanceAlgorithm = new RelevanceAlgorithmHelper(reviews);
            List<ReviewResponse> responses = relevanceAlgorithm.calculateRelevanceOfReviews();
            Collections.sort(responses, Comparator.comparing(ReviewResponse::getRelevanceScore,(s1,s2)-> {return (int) (s2-s1);}));
            return CommonUtils.getResponseObjForSuccess(responses);
        }catch (Exception e){
            return CommonUtils.getResponseObjForFailure(e.getMessage());
        }
    }

    private void addRelevantSkillGroup(UserDto userDto) {
        List<String> skills = userDto.getSkills().stream().map(Skill::getName).toList();
        SkillGroup group = reviewDao.fetchSkillGroupBasedOnSkills(skills);
        userDto.setSkillGroup(group);
    }

    private UserDto convertUserEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setCompany(user.getCompany());
        userDto.setSkills(user.getSkills());
        userDto.setYearOfExperience(user.getYearOfExperience());
        return userDto;
    }

    @Override
    public ResponseObj addReview(ReviewRequest reviewRequest) {
        String validationMessage = ReviewUtils.validateReviewRequestValid(reviewRequest);
        if(!StringUtils.isEmpty(validationMessage)){
            return CommonUtils.getResponseObjForFailure(validationMessage);
        }
        try{
            if(reviewRequest.getReviewer().getId()==reviewRequest.getReviewee().getId()){
                return CommonUtils.getResponseObjForFailure(CANT_SELF_REVIEW);
            }
            Skill skill = skillRepository.findSkillByNameAndUserId(reviewRequest.getSkill(),reviewRequest.getReviewee().getId());
            if(Objects.isNull(skill)){
                return CommonUtils.getResponseObjForFailure("There is no skill: "+reviewRequest.getSkill()+
                        " associated to user id:"+reviewRequest.getReviewee().getId());
            }
            Integer id = reviewDao.ifReviewExistsForGivenSkill(skill.getName(),reviewRequest.getReviewer().getId());
            if(!Objects.isNull(id)){
                reviewDao.updateReviewIfExists(reviewRequest,id);
                return CommonUtils.getResponseObjForSuccess(REVIEW_UPDATED);
            }
            Review review = new Review();
            UserDto reviewer = reviewRequest.getReviewer();
            UserDto reviewee = reviewRequest.getReviewee();
            review.setReviewee(new User(reviewee.getId()));
            review.setReviewer(new User(reviewer.getId()));
            review.setSkill(skill.getName());
            review.setScore(reviewRequest.getScore());
            reviewRepository.save(review);
            return CommonUtils.getResponseObjForSuccess(REVIEW_ADDED);
        }catch (Exception e){
            return CommonUtils.getResponseObjForFailure(e.getMessage());
        }
    }
}

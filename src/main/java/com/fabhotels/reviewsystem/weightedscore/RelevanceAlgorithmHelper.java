package com.fabhotels.reviewsystem.weightedscore;

import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.dto.ReviewResponse;
import com.fabhotels.reviewsystem.enitities.Skill;
import com.fabhotels.reviewsystem.utils.ReviewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for calculating relevance score of reviewes
 * @Author Akash Verma
 */
public class RelevanceAlgorithmHelper implements RelevanceAlgorithm{

    private List<ReviewRequest> reviews;

    public RelevanceAlgorithmHelper(List<ReviewRequest> reviews){
        this.reviews = reviews;

    }

    public List<ReviewResponse> calculateRelevanceOfReviews(){
        List<ReviewResponse> reviewResponses = new ArrayList<>();

        for(int i=0; i<reviews.size(); i++){
            ReviewResponse response = ReviewUtils.convertReviewObjectToReviewResponseObject(reviews.get(i));
            calculateCoworkerRelevance(reviews.get(i),response);
            calculateExperienceRelevance(reviews.get(i),response);
            calculateSkillClosenessRelevance(reviews.get(i),response);
            reviewResponses.add(response);
        }
        return  reviewResponses;
    }


    @Override
    public void calculateCoworkerRelevance(ReviewRequest review, ReviewResponse response) {
        int reviewerCompanyId = review.getReviewer().getCompany().getCompanyId();
        int revieweeComapnyId = review.getReviewee().getCompany().getCompanyId();
        if(revieweeComapnyId==reviewerCompanyId){
            response.addWeightedScore(WeightedScoreConstants.COWORKER_SCORE);
            response.addReason(WeightedScoreConstants.COWORKER_REASON);
        }else{
            response.addWeightedScore(WeightedScoreConstants.NON_COWORKER_SCORE);
            response.addReason(WeightedScoreConstants.NON_COWORKER_REASON);
        }
    }

    @Override
    public void calculateExperienceRelevance(ReviewRequest review, ReviewResponse response) {
        int reveiewerYOE = review.getReviewer().getYearOfExperience();
        int reveieweeYOE = review.getReviewee().getYearOfExperience();
        double score = 0;
        if(reveiewerYOE==reveieweeYOE){
            score = WeightedScoreConstants.SAME_YEAR_EXPERIENCE_SCORE;
            response.addReason(WeightedScoreConstants.SAME_YOE_REASON);
        }else {
            int diff = reveiewerYOE - reveieweeYOE;
            score = reveiewerYOE/reveieweeYOE;
            if(diff>0){
                response.addReason(WeightedScoreConstants.REVIEWER_HAS_MORE_YOE_REASON+diff);
            }else{
                response.addReason(WeightedScoreConstants.REVIEWEE_HAS_MORE_YOE_REASON+Math.abs(diff));
            }
        }
        response.addWeightedScore(score);
    }

    @Override
    public void calculateSkillClosenessRelevance(ReviewRequest review, ReviewResponse response) {
        Set<String> reviewerSkills = review.getReviewer().getSkills()
                .stream().map(Skill::getName).collect(Collectors.toSet());

        String reviewedSkill = review.getSkill();

        //check if reviewer has reviewed skill or not
        if(reviewerSkills.contains(reviewedSkill)){
            response.addWeightedScore(WeightedScoreConstants.RELEVANT_SKILL_REVIEWED_SCORE);
            response.addReason(WeightedScoreConstants.REVIEWER_HAVE_SAME_SKILL_AS_REVIEW_SKILL);
        }else{
            response.addWeightedScore(WeightedScoreConstants.IRRELEVANT_SKILL_REVIEWED_SCORE);
            response.addReason(WeightedScoreConstants.REVIEWER_DOES_NOT_HAVE_SAME_SKILL_AS_REVIEW_SKILL);
        }

        String reviewerSkillGroupName = review.getReviewer().getSkillGroup().getName();
        String revieweeSkillGroupName = review.getReviewee().getSkillGroup().getName();

        //check if reviewer and reviewee belongs to same skill group
        if(reviewerSkillGroupName.equals(revieweeSkillGroupName)){
            response.addWeightedScore(WeightedScoreConstants.SAME_SKILL_GROUP_WORKER);
            response.addReason(WeightedScoreConstants.REVIEWER_AND_REVIEWEE_SAME_SKILL_GROUP+revieweeSkillGroupName);
        }else{
            response.addWeightedScore(WeightedScoreConstants.DIFFERENT_SKILL_GROUP_WORKER);
            response.addReason(WeightedScoreConstants.REVIEWER_AND_REVIEWEE_DIFFERENT_SKILL_GROUP);
        }

    }

}

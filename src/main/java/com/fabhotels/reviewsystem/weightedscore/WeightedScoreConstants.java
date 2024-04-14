package com.fabhotels.reviewsystem.weightedscore;

public class WeightedScoreConstants {

    public static final Double COWORKER_SCORE = 1.0;
    public static final Double NON_COWORKER_SCORE = -1.0;
    public static final Double SAME_YEAR_EXPERIENCE_SCORE = 1.0;
    public static final Double RELEVANT_SKILL_REVIEWED_SCORE = 1.0;
    public static final Double IRRELEVANT_SKILL_REVIEWED_SCORE = -1.0;
    public static final Double SAME_SKILL_GROUP_WORKER = 1.0;
    public static final Double DIFFERENT_SKILL_GROUP_WORKER = -1.0;

    //reason
    public static final String COWORKER_REASON = "Both reviewer and reviewee are coworkers";
    public static final String NON_COWORKER_REASON = "Reviewer and reviewee are not coworkers";
    public static final String SAME_YOE_REASON = "Reviewer and reviewee has same year of experience";
    public static final String REVIEWER_HAS_MORE_YOE_REASON = "Reviewer has more years of experience than reviewee diff: ";
    public static final String REVIEWEE_HAS_MORE_YOE_REASON = "Reviewee has more years of experience than reviewer diff: ";
    public static final String REVIEWER_HAVE_SAME_SKILL_AS_REVIEW_SKILL = "Reviewer is skilled in reviewed skill";
    public static final String REVIEWER_DOES_NOT_HAVE_SAME_SKILL_AS_REVIEW_SKILL = "Reviewer is not skilled in reviewed skill";
    public static final String REVIEWER_AND_REVIEWEE_SAME_SKILL_GROUP = "Both Reviewer and Reviewee has same skill group: ";
    public static final String REVIEWER_AND_REVIEWEE_DIFFERENT_SKILL_GROUP = "Both Reviewer and Reviewee has different skill group";

}

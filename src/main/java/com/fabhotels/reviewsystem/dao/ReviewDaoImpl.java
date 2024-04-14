package com.fabhotels.reviewsystem.dao;

import com.fabhotels.reviewsystem.dto.ReviewRequest;
import com.fabhotels.reviewsystem.enitities.Skill;
import com.fabhotels.reviewsystem.enitities.SkillGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDaoImpl implements  ReviewDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate template){
        this.jdbcTemplate=template;
    }

    @Override
    public Integer ifReviewExistsForGivenSkill(String skill, Integer reviewerId) {
        String query = "select review_id from review where skill='"+skill+"' and reviewer_id="+reviewerId;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        if(result.isEmpty()){
            return null;
        }
        return (Integer) result.get(0).get("review_id");
    }

    @Override
    public void updateReviewIfExists(ReviewRequest reviewRequest, Integer id) {
        String query = "update review set score="+reviewRequest.getScore()+" where review_id="+id;
        jdbcTemplate.execute(query);
    }

    @Override
    public List<Map<String,Object>> fetchRequestByRevieweeId(Integer userId) {
        String query = "select r.*,s.name as skill_name " +
                "from review r " +
                "join skill s " +
                "on r.skill=s.name " +
                "where r.reviewee_id="+userId;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        if(result.isEmpty()){
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public SkillGroup fetchSkillGroupBasedOnSkills(List<String> skills){
        String skillsList = formatListForInQuery(skills);
        String query = "select sg.skill_group_id, sg.name\n" +
                "from skill s\n" +
                "join skill_group sg\n" +
                "on sg.skill_group_id = s.skill_group_id\n" +
                "where s.name in \n" + skillsList +
                "group by sg.skill_group_id\n" +
                "order by count(s.skill_id) desc\n" +
                "limit 1;";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        if(result.isEmpty()){
            return null;
        }
        return new SkillGroup((Integer)result.get(0).get("skill_group_id"),(String)result.get(0).get("name"));
    }

    private String formatListForInQuery(List<String> arguments) {
        StringBuilder builder = new StringBuilder("('");
        arguments.forEach(argument -> builder.append(argument+"','") );
        builder.setLength(builder.length()-2);
        builder.append(")");
        return builder.toString();
    }


}

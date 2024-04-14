package com.fabhotels.reviewsystem.dao;

import com.fabhotels.reviewsystem.enitities.Skill;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SkillDaoImpl implements SkillDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SkillDaoImpl(JdbcTemplate template){
        this.jdbcTemplate = template;
    }


    @Override
    public Skill findSkillByNameAndUserId(String name, Integer userId) throws Exception {
        if(StringUtils.isEmpty(name)) {
            throw new Exception("Skill name is blank");
        }
        String sql = "select s.*\n" +
                "from skill s\n" +
                "join user_skill_mapping m\n" +
                "on s.skill_id = m.skill_id\n" +
                "join user u\n" +
                "on u.user_id = m.user_id\n" +
                "where s.name='"+name+"' and u.user_id = "+userId+";";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        if(result.isEmpty()){
            return null;
        }
        Skill skill = new Skill();
        skill.setSkillId((Integer)result.get(0).get("skill_id"));
        skill.setName(String.valueOf(result.get(0).get("name")));
        return skill;
    }
}

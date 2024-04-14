package com.fabhotels.reviewsystem.dao;

import com.fabhotels.reviewsystem.enitities.Skill;

public interface SkillDao {

    Skill findSkillByNameAndUserId(String name, Integer userId) throws Exception;
}

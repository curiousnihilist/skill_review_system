package com.fabhotels.reviewsystem.enitities;

import jakarta.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "skill_group_id")
    private SkillGroup group;

    public Skill() {
    }

    public Skill(Integer skillId, String name) {
        this.skillId = skillId;
        this.name = name;
    }

    public SkillGroup getGroup() {
        return group;
    }

    public void setGroup(SkillGroup group) {
        this.group = group;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.fabhotels.reviewsystem.enitities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skill_group")
public class SkillGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_group_id")
    private Integer skillGroupId;

    private String name;

    public SkillGroup() {
    }

    public SkillGroup(Integer skillGroupId, String name) {
        this.skillGroupId = skillGroupId;
        this.name = name;
    }

    public Integer getSkillGroupId() {
        return skillGroupId;
    }

    public void setSkillGroupId(Integer skillGroupId) {
        this.skillGroupId = skillGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

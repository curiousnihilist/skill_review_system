package com.fabhotels.reviewsystem.dto;

import com.fabhotels.reviewsystem.enitities.Company;
import com.fabhotels.reviewsystem.enitities.Skill;
import com.fabhotels.reviewsystem.enitities.SkillGroup;

import java.util.Set;


public class UserDto {

    private int id;

    private String name;

    private Company company;

    private Integer yearOfExperience;

    private Set<Skill> skills;

    private SkillGroup skillGroup;

    public UserDto() {
    }

    public UserDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDto(int id, String name, Company company, Integer yearOfExperience, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.yearOfExperience = yearOfExperience;
        this.skills = skills;
    }

    public SkillGroup getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(SkillGroup skillGroup) {
        this.skillGroup = skillGroup;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(Integer yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }
}

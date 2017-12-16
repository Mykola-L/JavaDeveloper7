package com.system.management.project.models;

import java.util.HashSet;
import java.util.Set;

public class Developer implements IModel {

    private long id;
    private String name;
    private Company company;
    private Project project;
    private int salary;
    private Set<Skill> skills = new HashSet<>();

    public Developer() {
    }

    public Developer(String name) {
        this.name = name;
    }

    public Developer(long id, String name, int salary) {
        this(name);
        this.id = id;
        this.salary = salary;
    }

    public Developer(long id, String name, Company company, Project project, int salary) {
        this(id, name, salary);
        this.company = company;
        this.project = project;
    }

    public Developer(long id, String name, Company company, Project project, int salary, Set<Skill> skills) {
        this(id, name, company, project, salary);
        this.skills = skills;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Developer{")
                .append("id=").append(this.id)
                .append(", name='").append(this.name)
                .append(", salary=").append(this.salary)
                .append(", company=").append(this.company)
                .append(", project=").append(this.project)
                .append(", skills: ");
        for (Skill skill : this.skills) {
            stringBuilder.append(skill.getName()).append(", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary > 0 ? salary : 0;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<Skill> skills) {
        if (skills != null) {
            this.skills = new HashSet<>(skills);
        } else {
            this.skills = new HashSet<>();
        }
    }
}
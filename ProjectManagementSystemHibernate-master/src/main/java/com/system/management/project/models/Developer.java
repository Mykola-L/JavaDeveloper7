package com.system.management.project.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developers", schema = "projectmanagementdb")
public class Developer implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "salary")
    private int salary;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
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

    public boolean addSkill(Skill skill) {
        return (skill != null) && this.skills.add(skill);
    }
}
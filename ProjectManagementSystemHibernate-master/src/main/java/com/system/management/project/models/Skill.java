package com.system.management.project.models;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill implements IModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(long id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
        this.name = (name != null) ? name : "";
    }
}
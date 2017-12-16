package com.system.management.project.models;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company implements IModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(long id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", company='" + name + '\'' +
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
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }
}
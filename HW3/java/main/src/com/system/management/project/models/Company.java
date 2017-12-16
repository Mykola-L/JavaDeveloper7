package com.system.management.project.models;

public class Company implements IModel {

    private long id;
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
}
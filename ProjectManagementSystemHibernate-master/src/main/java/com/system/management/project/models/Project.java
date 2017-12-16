package com.system.management.project.models;

import javax.persistence.*;

@Entity
@Table(name = "projects", schema = "projectmanagementdb")
public class Project implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Project() {
    }

    public Project(String name, int cost, Company company, Customer customer) {
        this.name = name;
        this.cost = cost;
        this.company = company;
        this.customer = customer;
    }

    public Project(long id, String name, int cost, Company company, Customer customer) {
        this(name, cost, company, customer);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", company=" + company +
                ", customer=" + customer +
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost > 0 ? cost : 0;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
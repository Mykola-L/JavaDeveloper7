package com.system.management.project.controllers;

import com.system.management.project.dao.ICompanyDAO;
import com.system.management.project.dao.ICustomerDAO;
import com.system.management.project.dao.IGenericDAO;
import com.system.management.project.models.Company;
import com.system.management.project.models.Customer;
import com.system.management.project.models.Project;

import java.util.Scanner;

public class ProjectController extends AbstractModelController<Project> {

    protected ICompanyDAO companyDAO;
    protected ICustomerDAO customerDAO;

    public ProjectController(IGenericDAO<Project, Long> dao, ICompanyDAO companyDAO, ICustomerDAO customerDAO) {
        super(dao);
        this.companyDAO = companyDAO;
        this.customerDAO = customerDAO;
    }

    @Override
    protected Project getNevModel() {
        System.out.println("Input project name: ");
        String projectName = new Scanner(System.in).nextLine();
        System.out.println("Input project cost: ");
        int projectCost = new Scanner(System.in).nextInt();
        System.out.println("Input company id: ");
        long companyId = new Scanner(System.in).nextLong();
        System.out.println("Input customer id: ");
        long customerId = new Scanner(System.in).nextLong();
        Company company = companyDAO.findById(companyId);
        Customer customer = customerDAO.findById(customerId);
        Project project = new Project(-100, projectName, projectCost, company, customer);
        return project;
    }
}
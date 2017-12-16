package com.system.management.project.dao;

import com.system.management.project.models.Company;
import com.system.management.project.models.Customer;
import com.system.management.project.models.Developer;
import com.system.management.project.models.Project;

public interface IDeveloperDAO extends IGenericDAO<Developer, Long> {

    default Developer createNewEmptyDeveloper() {
        Developer developer = new Developer();
        Customer customer = new Customer(0, "");
        Company company = new Company(0, "");
        Project project = new Project(0, "", 0, company, customer);
        return new Developer(0, "", company, project, 0);
    }
}
package com.system.management.project.works;

import com.system.management.project.controllers.*;

import java.sql.SQLException;

public final class WorkController {

    private static CompanyController companyController;
    private static CustomerController customerController;
    private static DeveloperController developerController;
    private static ProjectController projectController;
    private static SkillController skillController;

    public WorkController() {
    }

    public static CompanyController getCompanyController() throws SQLException {
        if (companyController == null) {
            companyController = new CompanyController(WorkDao.getCompanyDAO());
        }
        return companyController;
    }

    public static CustomerController getCustomerController() throws SQLException {
        if (customerController == null) {
            customerController = new CustomerController(WorkDao.getCustomerDAO());
        }
        return customerController;
    }

    public static DeveloperController getDeveloperController() throws SQLException {
        if (developerController == null) {
            developerController = new DeveloperController(WorkDao.getDeveloperDAO(), WorkDao.getSkillDAO(),
                    WorkDao.getCompanyDAO(), WorkDao.getProjectDAO());
        }
        return developerController;
    }

    public static ProjectController getProjectController() throws SQLException {
        if (projectController == null) {
            projectController = new ProjectController(WorkDao.getProjectDAO(), WorkDao.getCompanyDAO(),
                    WorkDao.getCustomerDAO());
        }
        return projectController;
    }

    public static SkillController getSkillController() throws SQLException {
        if (skillController == null) {
            skillController = new SkillController(WorkDao.getSkillDAO());
        }
        return skillController;
    }
}
package com.system.management.project.factory;

import com.system.management.project.controllers.*;

import java.sql.SQLException;

public final class FactoryController {

    private static CompanyController companyController;
    private static CustomerController customerController;
    private static DeveloperController developerController;
    private static ProjectController projectController;
    private static SkillController skillController;

    public FactoryController() {
    }

    public static CompanyController getCompanyController() throws SQLException {
        if (companyController == null) {
            companyController = new CompanyController(FactoryDao.getCompanyDAO());
        }
        return companyController;
    }

    public static CustomerController getCustomerController() throws SQLException {
        if (customerController == null) {
            customerController = new CustomerController(FactoryDao.getCustomerDAO());
        }
        return customerController;
    }

    public static DeveloperController getDeveloperController() throws SQLException {
        if (developerController == null) {
            developerController = new DeveloperController(FactoryDao.getDeveloperDAO(), FactoryDao.getSkillDAO(),
                    FactoryDao.getCompanyDAO(), FactoryDao.getProjectDAO());
        }
        return developerController;
    }

    public static ProjectController getProjectController() throws SQLException {
        if (projectController == null) {
            projectController = new ProjectController(FactoryDao.getProjectDAO(), FactoryDao.getCompanyDAO(),
                    FactoryDao.getCustomerDAO());
        }
        return projectController;
    }

    public static SkillController getSkillController() throws SQLException {
        if (skillController == null) {
            skillController = new SkillController(FactoryDao.getSkillDAO());
        }
        return skillController;
    }
}
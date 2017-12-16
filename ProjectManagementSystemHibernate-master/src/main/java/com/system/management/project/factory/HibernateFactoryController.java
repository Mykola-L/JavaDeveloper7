package com.system.management.project.factory;

import com.system.management.project.controllers.*;

public final class HibernateFactoryController {

    private static CompanyController companyController;
    private static CustomerController customerController;
    private static DeveloperController developerController;
    private static ProjectController projectController;
    private static SkillController skillController;

    public HibernateFactoryController() {
    }

    public static CompanyController getCompanyController() {
        if (companyController == null) {
            companyController = new CompanyController(HibernateFactoryDao.getCompanyDAO());
        }
        return companyController;
    }

    public static CustomerController getCustomerController() {
        if (customerController == null) {
            customerController = new CustomerController(HibernateFactoryDao.getCustomerDAO());
        }
        return customerController;
    }

    public static DeveloperController getDeveloperController() {
        if (developerController == null) {
            developerController = new DeveloperController(
                    HibernateFactoryDao.getDeveloperDAO(),
                    HibernateFactoryDao.getSkillDAO(),
                    HibernateFactoryDao.getCompanyDAO(),
                    HibernateFactoryDao.getProjectDAO()
            );
        }
        return developerController;
    }

    public static ProjectController getProjectController() {
        if (projectController == null) {
            projectController = new ProjectController(
                    HibernateFactoryDao.getProjectDAO(),
                    HibernateFactoryDao.getCompanyDAO(),
                    HibernateFactoryDao.getCustomerDAO()
            );
        }
        return projectController;
    }

    public static SkillController getSkillController() {
        if (skillController == null) {
            skillController = new SkillController(HibernateFactoryDao.getSkillDAO());
        }
        return skillController;
    }
}

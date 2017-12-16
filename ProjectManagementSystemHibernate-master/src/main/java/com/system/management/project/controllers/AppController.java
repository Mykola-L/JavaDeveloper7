package com.system.management.project.controllers;

import java.sql.SQLException;

public class AppController extends AbstractController {

    private CompanyController companyController;
    private CustomerController customerController;
    private DeveloperController developerController;
    private ProjectController projectController;
    private SkillController skillController;

    public AppController(CompanyController companyController,
                         CustomerController customerController,
                         DeveloperController developerController,
                         ProjectController projectController,
                         SkillController skillController) {
        this.companyController = companyController;
        this.customerController = customerController;
        this.developerController = developerController;
        this.projectController = projectController;
        this.skillController = skillController;
    }

    @Override
    protected void action(int choice) throws SQLException {
        switch (choice) {
            case 1:
                companyController.start();
                break;
            case 2:
                customerController.start();
                break;
            case 3:
                developerController.start();
                break;
            case 4:
                projectController.start();
                break;
            case 5:
                skillController.start();
                break;
        }
    }

    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("MENU");
        System.out.println("1 - use companies");
        System.out.println("2 - use customers");
        System.out.println("3 - use developers");
        System.out.println("4 - use projects");
        System.out.println("5 - use skills");
        System.out.println("0 - exit");
    }
}
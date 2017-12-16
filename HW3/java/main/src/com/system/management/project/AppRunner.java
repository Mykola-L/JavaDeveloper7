/*
Крилевец Александр

Необходимо создать консольное приложение, которое:

    - использует БД, созданную в домашнем задании для пункта 1.2
    - позволяет выполнять CRUD (CREATE, READ, UPDATE, DELETE) операции для таблиц:
        - developers
        - skills
        - companies
        - customers
        - projects
Пример: Создать разработчика, добавить ему навыки. Создать проект, и добавить в данный проект разработчиков.
Разрешается использовать все возможности JDBC

Результатом выполнения должен быть созданный ОТДЕЛЬНЫЙ репозиторий на Bitbucket под названием ProjectManagementSystem
 */

package com.system.management.project;

import com.system.management.project.controllers.AbstractController;
import com.system.management.project.controllers.AppController;
import com.system.management.project.works.WorkController;

import java.sql.SQLException;

public class AppRunner extends AbstractController {

    public static void main(String[] args) {
        try {
            new AppRunner().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Bye!");
    }

    @Override
    protected void action(int choice) throws SQLException {
        switch (choice) {
            case 1:
                new AppController(WorkController.getCompanyController(),
                        WorkController.getCustomerController(),
                        WorkController.getDeveloperController(),
                        WorkController.getProjectController(),
                        WorkController.getSkillController())
                        .start();
                break;
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("Make your choice:");
        System.out.println("1 - use JDBC");
        System.out.println("0 - exit");
    }
}
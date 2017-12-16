/*
Крилевец Александр
Неоходимо реализовать Hibernate реализацию слоя DAO для Домашнего задания модуля 2.
Весь функционал и внешний вид приложения должны оставаться без изменений.
 */
package com.system.management.project;

import com.system.management.project.controllers.AbstractController;
import com.system.management.project.controllers.AppController;
import com.system.management.project.factory.HibernateFactoryController;
import com.system.management.project.factory.FactoryController;

import java.sql.SQLException;

public class AppRunner extends AbstractController {

    public static void main(String[] args) {
        try {
            new AppRunner().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Bye!");
        System.exit(0);
    }

    @Override
    protected void action(int choice) throws SQLException {
        switch (choice) {
            case 1:
                new AppController(FactoryController.getCompanyController(),
                        FactoryController.getCustomerController(),
                        FactoryController.getDeveloperController(),
                        FactoryController.getProjectController(),
                        FactoryController.getSkillController())
                        .start();
                break;
            case 2:
                new AppController(HibernateFactoryController.getCompanyController(),
                        HibernateFactoryController.getCustomerController(),
                        HibernateFactoryController.getDeveloperController(),
                        HibernateFactoryController.getProjectController(),
                        HibernateFactoryController.getSkillController())
                        .start();
                break;
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("Make your choice:");
        System.out.println("1 - use JDBC");
        System.out.println("2 - use Hibernate");
        System.out.println("0 - exit");
    }
}
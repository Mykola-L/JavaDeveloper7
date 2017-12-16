package com.system.management.project.factory;

import com.system.management.project.connections.ConnectionMySql;
import com.system.management.project.dao.*;
import com.system.management.project.dao.jdbc.*;

import java.sql.SQLException;

public final class FactoryDao {

    private static ICompanyDAO companyDAO;
    private static ICustomerDAO customerDAO;
    private static IDeveloperDAO developerDAO;
    private static IProjectDAO projectDAO;
    private static ISkillDAO skillDAO;

    private FactoryDao() {
    }

    public static ICompanyDAO getCompanyDAO() throws SQLException {
        if (companyDAO == null) {
            companyDAO = createCompanyDao();
        }
        return companyDAO;
    }

    private static ICompanyDAO createCompanyDao() throws SQLException {
        return new JdbcCompanyDAO(ConnectionMySql.getInstatce());
    }

    public static ICustomerDAO getCustomerDAO() throws SQLException {
        if (customerDAO == null) {
            customerDAO = createCustomerDao();
        }
        return customerDAO;
    }

    private static ICustomerDAO createCustomerDao() throws SQLException {
        return new JdbcCustomerDAO(ConnectionMySql.getInstatce().getConnection());
    }

    public static IDeveloperDAO getDeveloperDAO() throws SQLException {
        if (developerDAO == null) {
            developerDAO = createDeveloperDao();
        }
        return developerDAO;
    }

    private static IDeveloperDAO createDeveloperDao() throws SQLException {
        return new JdbcDeveloperDAO(ConnectionMySql.getInstatce());
    }

    public static IProjectDAO getProjectDAO() throws SQLException {
        if (projectDAO == null) {
            projectDAO = createProjectDao();
        }
        return projectDAO;
    }

    private static IProjectDAO createProjectDao() throws SQLException {
        return new JdbcProjectDAO(ConnectionMySql.getInstatce().getConnection());
    }

    public static ISkillDAO getSkillDAO() throws SQLException {
        if (skillDAO == null) {
            skillDAO = createSkillDao();
        }
        return skillDAO;
    }

    private static ISkillDAO createSkillDao() throws SQLException {
        return new JdbcSkillDAO(ConnectionMySql.getInstatce());
    }
}
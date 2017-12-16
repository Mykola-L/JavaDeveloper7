package com.system.management.project.factory;

import com.system.management.project.dao.*;
import com.system.management.project.dao.hibernate.*;
import com.system.management.project.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateFactoryDao {

    private static SessionFactory sessionFactory;
    private static ICompanyDAO companyDAO;
    private static ICustomerDAO customerDAO;
    private static IDeveloperDAO developerDAO;
    private static IProjectDAO projectDAO;
    private static ISkillDAO skillDAO;

    public HibernateFactoryDao() {
    }

    public static SessionFactory getSessionFactory() {
        if ((sessionFactory == null) || (sessionFactory.isClosed())) {
            sessionFactory = new Configuration()
                    .configure("META-INF/persistence.xml")
                    .addAnnotatedClass(Company.class)
                    .addAnnotatedClass(Developer.class)
                    .addAnnotatedClass(Skill.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Customer.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static ICompanyDAO getCompanyDAO() {
        if (companyDAO == null) {
            companyDAO = new HibernateCompanyDAO(getSessionFactory());
        }
        return companyDAO;
    }

    public static ICustomerDAO getCustomerDAO() {
        if (customerDAO == null) {
            customerDAO = new HibernateCustomerDAO(getSessionFactory());
        }
        return customerDAO;
    }

    public static IDeveloperDAO getDeveloperDAO() {
        if (developerDAO == null) {
            developerDAO = new HibernateDeveloperDAO(getSessionFactory());
        }
        return developerDAO;
    }

    public static IProjectDAO getProjectDAO() {
        if (projectDAO == null) {
            projectDAO = new HibernateProjectDAO(getSessionFactory());
        }
        return projectDAO;
    }

    public static ISkillDAO getSkillDAO() {
        if (skillDAO == null) {
            skillDAO = new HibernateSkillDAO(getSessionFactory());
        }
        return skillDAO;
    }
}

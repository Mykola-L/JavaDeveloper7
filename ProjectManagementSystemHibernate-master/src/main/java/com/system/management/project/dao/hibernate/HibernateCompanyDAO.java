package com.system.management.project.dao.hibernate;

import com.system.management.project.dao.ICompanyDAO;
import com.system.management.project.models.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateCompanyDAO implements ICompanyDAO {

    private SessionFactory sessionFactory;

    public HibernateCompanyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void update(Company company) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Company companyFromDb = session.get(Company.class, company.getId());
            if (companyFromDb == null) {
                return;
            }
            companyFromDb.setName(company.getName());
            session.update(companyFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update company " + company);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Company findById(Long id) {
        Company company = new Company(id, "");
        try (Session session = sessionFactory.openSession()) {
            Company companyFromDb = session.get(Company.class, id);
            if (companyFromDb != null) {
                company = companyFromDb;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find company with id: " + id);
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public List<Company> read() {
        List<Company> companies = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            companies = session.createQuery("from Company").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all companies");
            e.printStackTrace();
        }
        return companies;
    }

    @Override
    public void delete(Company company) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Company companyFromDb = session.get(Company.class, company.getId());
            if (companyFromDb == null) {
                return;
            }
            session.delete(companyFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete company " + company);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Long create(Company company) {
        Long id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(company);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to create company " + company);
        }
        return id;
    }
}

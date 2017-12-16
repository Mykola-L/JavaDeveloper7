package com.system.management.project.dao.hibernate;

import com.system.management.project.dao.ICustomerDAO;
import com.system.management.project.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateCustomerDAO implements ICustomerDAO {

    private SessionFactory sessionFactory;

    public HibernateCustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Customer customer) {
        Long id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(customer);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to save customer " + customer);
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Customer> read() {
        List<Customer> customers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            customers = session.createQuery("from Customer").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all customers");
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void delete(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Customer customerFromDb = session.get(Customer.class, customer.getId());
            if (customerFromDb == null) {
                return;
            }
            session.delete(customerFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete  customer " + customer);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public Customer findById(Long id) {
        Customer customer = new Customer(id, "");
        try (Session session = sessionFactory.openSession()) {
            Customer customerFromDb = session.get(Customer.class, id);
            if (customerFromDb != null) {
                customer = customerFromDb;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred  while trying to find customer with id: " + id);
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Customer customerFromDb = session.get(Customer.class, customer.getId());
            if (customerFromDb == null) {
                return;
            }
            customerFromDb.setName(customer.getName());
            session.update(customerFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update customer " + customer);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

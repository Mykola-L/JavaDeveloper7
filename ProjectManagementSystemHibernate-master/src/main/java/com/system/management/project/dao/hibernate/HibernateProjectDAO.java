package com.system.management.project.dao.hibernate;

import com.system.management.project.dao.IProjectDAO;
import com.system.management.project.models.Company;
import com.system.management.project.models.Customer;
import com.system.management.project.models.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateProjectDAO implements IProjectDAO {

    private SessionFactory sessionFactory;

    public HibernateProjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Project project) {
        Long id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Long) session.save(project);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to save project " + project);
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Project> read() {
        List<Project> projects = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            projects = session.createQuery("from Project").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all projects");
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public void update(Project project) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Project projectForDb = session.get(Project.class, project.getId());
            if (projectForDb == null) {
                return;
            }
            projectForDb.setName(project.getName());
            projectForDb.setCost(project.getCost());
            projectForDb.setCompany(project.getCompany());
            projectForDb.setCustomer(project.getCustomer());
            session.update(projectForDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update project " + project);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Project project) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Project projectFromDb = session.get(Project.class, project.getId());
            if (projectFromDb == null) {
                return;
            }
            session.delete(projectFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete project " + project);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Project findById(Long id) {
        Project project =
                new Project(id, "", 0, new Company(0, ""), new Customer(0, ""));
        try (Session session = sessionFactory.openSession()) {
            Project projectFromDb = session.get(Project.class, id);
            if (projectFromDb != null) {
                project = projectFromDb;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying  to find project with id: " + id);
            e.printStackTrace();
        }
        return project;
    }
}

package com.system.management.project.dao.hibernate;

import com.system.management.project.dao.IDeveloperDAO;
import com.system.management.project.models.Developer;
import com.system.management.project.models.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;

public class HibernateDeveloperDAO implements IDeveloperDAO {

    private SessionFactory sessionFactory;

    public HibernateDeveloperDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Developer developer) {
        Long id = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            id = (Long) session.save(developer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public List<Developer> read() {
        List<Developer> developers;
        try (Session session = sessionFactory.openSession()) {
            developers = session.createQuery("from Developer").list();
        }
        return developers;
    }

    @Override
    public void update(Developer developer) {
        Developer developerNew;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            developerNew = session.get(Developer.class, developer.getId());
            if (developerNew != null) {
                developerNew.setName(developer.getName());
                developerNew.setSalary(developer.getSalary());
                developerNew.setCompany(developer.getCompany());
                developerNew.setProject(developer.getProject());
                developerNew.setSkills(new HashSet<>());
                for (Skill skill : developer.getSkills()) {
                    developerNew.addSkill(skill);
                }
                session.clear();
                session.update(developerNew);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void delete(Developer obj) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, obj.getId());
            if (developer != null) {
                session.delete(developer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Developer findById(Long id) {
        Developer developer = createNewEmptyDeveloper();
        developer.setId(id);
        try (Session session = sessionFactory.openSession()) {
            Developer developerFromDb = session.get(Developer.class, id);
            if (developerFromDb != null) {
                developer = developerFromDb;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return developer;
    }
}

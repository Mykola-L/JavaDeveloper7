package com.system.management.project.dao.hibernate;

import com.system.management.project.dao.ISkillDAO;
import com.system.management.project.models.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateSkillDAO implements ISkillDAO {

    private SessionFactory sessionFactory;

    public HibernateSkillDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Skill obj) {
        Long id;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            id = (Long) session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public List<Skill> read() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Skill").list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Skill obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill skillToUpdate = session.get(Skill.class, obj.getId());
            if (skillToUpdate != null) {
                skillToUpdate.setName(obj.getName());
                session.update(skillToUpdate);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Skill obj) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill skillToDelete = session.get(Skill.class, obj.getId());
            if (skillToDelete != null) {
                session.delete(skillToDelete);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Skill findById(Long aLong) {
        Skill skill = new Skill(aLong, "");
        try (Session session = sessionFactory.openSession()) {
            Skill skillFromDb = session.get(Skill.class, aLong);
            if (skillFromDb != null) {
                skill = skillFromDb;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return skill;
    }
}

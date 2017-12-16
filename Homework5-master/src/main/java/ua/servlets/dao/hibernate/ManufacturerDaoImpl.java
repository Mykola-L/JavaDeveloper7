package ua.servlets.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.servlets.dao.IManufacturerDao;
import ua.servlets.model.Manufacturer;

import java.util.List;
import java.util.UUID;

public class ManufacturerDaoImpl implements IManufacturerDao {

    public ManufacturerDaoImpl() {
    }

    public Manufacturer getByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Manufacturer m WHERE m.name =:name");
        query.setParameter("name", name);
        Manufacturer manufacturer = (Manufacturer) query.uniqueResult();
        transaction.commit();
        session.close();
        return manufacturer;
    }

    public void save(Manufacturer manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        manufacturer.setId(UUID.randomUUID());
        session.save(manufacturer);
        transaction.commit();
        session.close();

    }

    public void delete(Manufacturer manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(manufacturer);
        transaction.commit();
        session.close();
    }

    public void update(Manufacturer manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(manufacturer);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Manufacturer> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Manufacturer> result = session.createQuery("FROM Manufacturer").list();
        return result;
    }

    public Manufacturer getById(UUID uuid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Manufacturer m WHERE m.id =:id");
        query.setParameter("id", uuid);

        Manufacturer manufacturer = (Manufacturer) query.uniqueResult();
        transaction.commit();
        session.close();
        return manufacturer;
    }
}

package ua.servlets.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.servlets.dao.IProductDao;
import ua.servlets.model.Product;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ProductDaoImpl implements IProductDao {

    public void save(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        product.setId(randomUUID());
        session.save(product);
        transaction.commit();
        session.close();
    }

    public void delete(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(product);
        transaction.commit();
        session.close();
    }

    public void update(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(product);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Product> result = session.createQuery("FROM Product").list();
        return result;
    }

    public Product getById(UUID uuid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Product p WHERE p.id =:id");
        query.setParameter("id", uuid);
        Product product = (Product) query.uniqueResult();
        transaction.commit();
        session.close();
        return product;
    }
}

package ua.servlets;

import ua.servlets.dao.IManufacturerDao;
import ua.servlets.dao.IProductDao;
import ua.servlets.dao.hibernate.ManufacturerDaoImpl;
import ua.servlets.dao.hibernate.ProductDaoImpl;

public class DaoSingleton {
    private IManufacturerDao manufacturerDAO;
    private IProductDao productDAO;
    private static final DaoSingleton INSTANCE = new DaoSingleton();

    private DaoSingleton() {
        manufacturerDAO = new ManufacturerDaoImpl();
        productDAO = new ProductDaoImpl();
    }

    public IManufacturerDao getManufacturerDAO() {
        return manufacturerDAO;
    }

    public IProductDao getProductDAO() {
        return productDAO;
    }

    public static DaoSingleton getINSTANCE() {
        return INSTANCE;
    }
}

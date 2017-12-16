package ua.servlets.dao;

import java.util.List;

public interface IGenericDao<T, ID> {

    void save(T t);

    void delete(T t);

    void update(T t);

    List<T> getAll();

    T getById(ID id);
}

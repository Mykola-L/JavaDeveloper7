package com.system.management.project.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, ID extends Serializable> {

    ID create(T obj);

    List<T> read();

    void update(T obj);

    void delete(T obj);

    T findById(ID id);
}
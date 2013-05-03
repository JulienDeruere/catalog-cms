package com.catalog.dao;

import java.util.List;

public interface IGenericDao<T> {

    public void persist(T transientInstance);

    public void remove(T transientInstance);

    public T merge(T transientInstance);

    public List<T> findAll();
}
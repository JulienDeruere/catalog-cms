package com.catalog.dao;

import java.io.Serializable;

public interface IGenericIdDao<T, PK extends Serializable> extends IGenericDao<T> {

    public T findById(PK id);
}
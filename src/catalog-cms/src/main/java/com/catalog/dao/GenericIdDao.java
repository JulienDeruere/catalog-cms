package com.catalog.dao;

import java.io.Serializable;

public abstract class GenericIdDao<T, PK extends Serializable> extends GenericDao<T> implements IGenericIdDao<T, PK> {

    public GenericIdDao(Class<T> clazz) {
	super(clazz);
    }

    @Override
    public T findById(PK id) {
	try {
	    T instance = entityManager.find(clazz, id);
	    return instance;
	} catch (RuntimeException re) {
	    throw re;
	}
    }
}
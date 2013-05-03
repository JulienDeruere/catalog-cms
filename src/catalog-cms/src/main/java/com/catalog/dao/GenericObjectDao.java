package com.catalog.dao;


public abstract class GenericObjectDao<T> extends GenericIdDao<T, Long> {

    public GenericObjectDao(Class<T> clazz) {
	super(clazz);
    }
}
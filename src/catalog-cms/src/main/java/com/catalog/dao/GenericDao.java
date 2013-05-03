package com.catalog.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> clazz;

    public GenericDao(Class<T> clazz) {
	this.clazz = clazz;
    }

    @Override
    public void persist(T transientInstance) {
	entityManager.persist(transientInstance);
    }

    @Override
    public void remove(T transientInstance) {
	entityManager.remove(transientInstance);
    }

    @Override
    public T merge(T transientInstance) {
	return entityManager.merge(transientInstance);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
	String query = "Select t From " + clazz.getName() + " t";
	return entityManager.createQuery(query).getResultList();
    }

    protected Session getHibernateSession() {
	return entityManager.unwrap(Session.class);
    }
}
package com.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import com.catalog.dao.GenericObjectDao;
import com.catalog.domain.Catalog;

@Repository
public class CatalogDao extends GenericObjectDao<Catalog> {

    public CatalogDao() {
	super(Catalog.class);
    }
}

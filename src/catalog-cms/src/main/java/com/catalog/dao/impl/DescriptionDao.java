package com.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import com.catalog.dao.GenericObjectDao;
import com.catalog.domain.Description;

@Repository
public class DescriptionDao extends GenericObjectDao<Description> {

    public DescriptionDao() {
	super(Description.class);
    }
}

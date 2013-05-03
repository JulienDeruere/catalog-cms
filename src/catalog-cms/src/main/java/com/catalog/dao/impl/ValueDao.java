package com.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import com.catalog.dao.GenericObjectDao;
import com.catalog.domain.Value;

@Repository
public class ValueDao extends GenericObjectDao<Value> {

    public ValueDao() {
	super(Value.class);
    }
}

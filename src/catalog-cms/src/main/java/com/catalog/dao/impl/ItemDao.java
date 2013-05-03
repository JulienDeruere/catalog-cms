package com.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import com.catalog.dao.GenericObjectDao;
import com.catalog.domain.Item;

@Repository
public class ItemDao extends GenericObjectDao<Item> {

    public ItemDao() {
	super(Item.class);
    }
}

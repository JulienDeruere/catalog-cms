package com.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import com.catalog.dao.GenericObjectDao;
import com.catalog.domain.Section;

@Repository
public class SectionDao extends GenericObjectDao<Section> {

    public SectionDao() {
	super(Section.class);
    }
}

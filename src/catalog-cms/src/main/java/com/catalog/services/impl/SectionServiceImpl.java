package com.catalog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.dao.impl.SectionDao;
import com.catalog.domain.Section;
import com.catalog.services.SectionService;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDao sectionDao;
    
    @Override
    public void delete(Section section) {
	section = sectionDao.merge(section);
	sectionDao.remove(section);
    }

    @Override
    public void add(Section section) {
	sectionDao.persist(section);
    }

    @Override
    public void edit(Section section) {
	sectionDao.merge(section);
    }
}
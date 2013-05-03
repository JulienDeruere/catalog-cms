package com.catalog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.dao.impl.ItemDao;
import com.catalog.dao.impl.SectionDao;
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.services.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SectionDao sectionDao;
    
    @Override
    public void delete(Item item, Section section) {
	section.getItems().remove(item);
	sectionDao.merge(section);
    }

    @Override
    public void add(Item item, Section section) {
	section.getItems().add(item);
	sectionDao.merge(section);
    }

    @Override
    public void edit(Item item) {
	itemDao.merge(item);
    }
}
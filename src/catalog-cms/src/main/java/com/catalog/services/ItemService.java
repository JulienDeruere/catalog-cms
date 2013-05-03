package com.catalog.services;

import org.springframework.transaction.annotation.Transactional;

import com.catalog.domain.Item;
import com.catalog.domain.Section;

public interface ItemService {

    @Transactional
    public void delete(Item item, Section section);

    @Transactional
    public void add(Item item, Section section);
    
    @Transactional
    public void edit(Item item);
}
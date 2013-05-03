package com.catalog.services;

import org.springframework.transaction.annotation.Transactional;

import com.catalog.domain.Section;

public interface SectionService {

    @Transactional
    public void delete(Section section);

    @Transactional
    public void add(Section section);

    @Transactional
    public void edit(Section section);
}
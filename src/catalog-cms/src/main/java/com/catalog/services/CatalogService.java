package com.catalog.services;

import java.io.File;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.catalog.domain.Catalog;

public interface CatalogService {

    public Catalog get(Long id);
    
    public List<Catalog> getAll();

    @Transactional
    public void add(Catalog catalog);
    
    @Transactional
    public void edit(Catalog catalog);
    
    public File exportCatalogAsJson(Catalog catalog);
}
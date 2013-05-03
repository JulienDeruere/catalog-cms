package com.catalog.bean;

import com.catalog.domain.Catalog;
import com.catalog.services.CatalogService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "catalogList")
public class CatalogList implements Serializable {

    private List<Catalog> catalogs;

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService;

    @PostConstruct
    protected void init() {
	catalogs = catalogService.getAll();
    }

    public List<Catalog> getCatalogs() {
	return catalogs;
    }

    public void setCatalogService(CatalogService catalogService) {
	this.catalogService = catalogService;
    }
}
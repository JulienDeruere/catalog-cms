package com.catalog.bean;

import com.catalog.domain.Catalog;
import com.catalog.services.CatalogService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "catalogForm")
public class CatalogForm implements Serializable {

    private Catalog catalog = new Catalog();

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService;
   
    @ManagedProperty("#{catalogList}")
    private CatalogList catalogList;
    
    public void add() {
	catalogService.add(catalog);
	catalogList.getCatalogs().add(catalog);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Catalogue enregistré"));
	catalog = new Catalog();
    }
    
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalogService(CatalogService catalogService) {
	this.catalogService = catalogService;
    }

    public void setCatalogList(CatalogList catalogList) {
        this.catalogList = catalogList;
    }
}

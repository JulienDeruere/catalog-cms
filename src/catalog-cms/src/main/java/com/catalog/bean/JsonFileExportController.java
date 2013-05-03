package com.catalog.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.catalog.domain.Catalog;
import com.catalog.services.CatalogService;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "jsonFileExportController")
public class JsonFileExportController implements Serializable {

    private StreamedContent file;

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService;

    public void export(Catalog catalog) {
	File zipFile = catalogService.exportCatalogAsJson(catalog);
	try {
	    file = new DefaultStreamedContent(new FileInputStream(zipFile), "application/zip", zipFile.getName());
	} catch (FileNotFoundException e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Impossible de générer le fichier"));
	}
    }

    public StreamedContent getFile() {
	return file;
    }

    public void setCatalogService(CatalogService catalogService) {
	this.catalogService = catalogService;
    }
}

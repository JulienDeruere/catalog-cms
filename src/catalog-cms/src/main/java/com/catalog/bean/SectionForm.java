package com.catalog.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.catalog.domain.Catalog;
import com.catalog.domain.Description;
import com.catalog.domain.Image;
import com.catalog.domain.Section;
import com.catalog.domain.Type;
import com.catalog.services.ImageService;
import com.catalog.services.SectionService;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "sectionForm")
public class SectionForm implements Serializable {

    private Section section;

    @ManagedProperty("#{sectionService}")
    private SectionService sectionService;
    
    @ManagedProperty("#{imageService}")
    private ImageService imageService;

    @ManagedProperty("#{catalogTreeTable}")
    private CatalogTreeTable catalogTreeTable;

    @PostConstruct
    public void init() {
	section = new Section();
	section.setImage(new Image());
    }

    public void handleFileUpload(FileUploadEvent event) {
	Image image = section.getImage();
	imageService.init(image, event.getFile());
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Image " + event.getFile().getFileName() + " associée"));
    }
      
    public StreamedContent getImageContent() {
	StreamedContent content = new DefaultStreamedContent();
	if (section.getImage().getContents() != null)
	    content = new DefaultStreamedContent(new ByteArrayInputStream(section.getImage().getContents()), section.getImage().getMimeType());
	return content;
    }

    public void add(Catalog catalog, List<Description> descriptions) {
	section.setCatalog(catalog);
	section.setType(Type.LOCAL);
	section.setDescription(descriptions);
	sectionService.add(section);
	catalogTreeTable.choose(catalog);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Section enregistrée"));
	init();
    }

    public void edit(List<Description> descriptions) {
	section.setType(Type.LOCAL);
	section.setDescription(descriptions);
	sectionService.edit(section);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Section editée"));
	init();
    }

    public Section getSection() {
	return section;
    }

    public void setSection(Section section) {
	this.section = section;
    }

    public void setSectionService(SectionService sectionService) {
	this.sectionService = sectionService;
    }
    
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setCatalogTreeTable(CatalogTreeTable catalogTreeTable) {
	this.catalogTreeTable = catalogTreeTable;
    }
}

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
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.domain.Type;
import com.catalog.services.ImageService;
import com.catalog.services.ItemService;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "itemForm")
public class ItemForm implements Serializable {

    private Item item;
    
    @ManagedProperty("#{itemService}")
    private ItemService itemService;
 
    @ManagedProperty("#{imageService}")
    private ImageService imageService;
    
    @ManagedProperty("#{catalogTreeTable}")
    private CatalogTreeTable catalogTreeTable;
    
    @PostConstruct
    public void init() {
	item = new Item();
	item.setImage(new Image());
    }

    public void handleFileUpload(FileUploadEvent event) {
	Image image = item.getImage();
	imageService.init(image, event.getFile());
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Image " + event.getFile().getFileName() + " associée"));
    }
    
    public StreamedContent getImageContent() {
	StreamedContent content = new DefaultStreamedContent();
	if (item.getImage().getContents() != null)
	    content = new DefaultStreamedContent(new ByteArrayInputStream(item.getImage().getContents()), item.getImage().getMimeType());
	return content;
    }

    public void add(Section section, List<Description> descriptions) {
	item.setType(Type.LOCAL);
	item.setDescription(descriptions);
	itemService.add(item, section);
	catalogTreeTable.choose((Catalog) catalogTreeTable.getRoot().getData());
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Item enregistré"));
	init();
    }

    public void edit(List<Description> descriptions) {
	item.setType(Type.LOCAL);
	item.setDescription(descriptions);
	itemService.edit(item);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Item édité"));
	init();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
    
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setCatalogTreeTable(CatalogTreeTable catalogTreeTable) {
        this.catalogTreeTable = catalogTreeTable;
    }
}

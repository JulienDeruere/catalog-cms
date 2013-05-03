package com.catalog.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.catalog.domain.Catalog;
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.services.CatalogService;
import com.catalog.services.ItemService;
import com.catalog.services.SectionService;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "catalogTreeTable")
public class CatalogTreeTable implements Serializable {

    private static final String CATALOG = "catalog";
    private static final String SECTION = "section";
    private static final String ITEM = "item";

    private TreeNode root;
    private TreeNode selectedNode;
    private Item copyItem;

    @ManagedProperty("#{catalogService}")
    private CatalogService catalogService;
    
    @ManagedProperty("#{sectionService}")
    private SectionService sectionService;

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    public void choose(Catalog catalog) {
	catalog = catalogService.get(catalog.getId());
	root = new DefaultTreeNode(CATALOG, catalog, null);
	for (Section section : catalog.getSections())
	    addSectionNode(section, root);
	copyItem = null;
    }
    
    public void edit(Catalog catalog) {
	catalogService.edit(catalog);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Catalogue édité"));
    }
    
    public void copyItem() {
	this.copyItem = (Item) selectedNode.getData();
    }

    public void pasteItem() {
	Section section = (Section) selectedNode.getData();
	section.getItems().add(copyItem);
	sectionService.edit(section);
	addItemNode(copyItem, selectedNode);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Item associé"));
    }
    
    /**
     * Add a section node to the catalog root node
     * 
     * @param section
     */
    public void addSectionNode(Section section) {
	this.addSectionNode(section, root);
    }

    /**
     * Add a section node to the parent node
     * 
     * @param section
     * @param parent
     */
    public void addSectionNode(Section section, TreeNode parent) {
	TreeNode root = new DefaultTreeNode(SECTION, section, parent);
	for (Section subSection : section.getSubSections())
	    addSectionNode(subSection, root);
	for (Item item : section.getItems())
	    addItemNode(item, root);
    }

    public void addItemNode(Item item, TreeNode parent) {
	new DefaultTreeNode(ITEM, item, parent);
    }

    public void deleteSectionNode() {
	Section section = (Section) selectedNode.getData();
	sectionService.delete(section);

	switch (selectedNode.getParent().getType()) {
	case CATALOG:
	    Catalog catalog = (Catalog) selectedNode.getParent().getData();
	    catalog.getSections().remove(section);
	    break;
	case SECTION:
	    Section subSection = (Section) selectedNode.getParent().getData();
	    section.getSubSections().remove(subSection);
	    break;
	default:
	    break;
	}
	
	deleteNode();
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Section supprimée"));
    }

    public void deleteItemNode() {
	Item item = (Item) selectedNode.getData();
	Section section = (Section) selectedNode.getParent().getData();
	itemService.delete(item, section);
	deleteNode();
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Item supprimé"));
    }

    public void deleteNode() {
	selectedNode.getChildren().clear();
	selectedNode.getParent().getChildren().remove(selectedNode);
	selectedNode.setParent(null);
	selectedNode = null;
    }

    public TreeNode getRoot() {
	return root;
    }

    public TreeNode getSelectedNode() {
	return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
	this.selectedNode = selectedNode;
    }
    
    public Item getCopyItem() {
        return copyItem;
    }

    public void setCopyItem(Item copyItem) {
        this.copyItem = copyItem;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void setSectionService(SectionService sectionService) {
	this.sectionService = sectionService;
    }

    public void setItemService(ItemService itemService) {
	this.itemService = itemService;
    }
}
package com.catalog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SuppressWarnings("serial")
public class Section extends GenericObject {

    private Catalog catalog;
    private String name;
    private Type type;
    private Image image;
    private List<Section> subSections = new ArrayList<>(0);
    private List<Item> items = new ArrayList<>(0);
    private List<Description> description;

    public Section() {
    }

    public Section(String name, Type type, Image image) {
	super();
	this.name = name;
	this.type = type;
	this.image = image;
    }

    public Section(Catalog catalog, String name, Type type, Image image) {
	this(name, type, image);
	this.catalog = catalog;
    }

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    public Catalog getCatalog() {
	return catalog;
    }

    public void setCatalog(Catalog catalog) {
	this.catalog = catalog;
    }

    @Column(nullable = false)
    @NotEmpty(message = "{section.name.not_empty}")
    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Enumerated
    @Column(nullable = false)
    public Type getType() {
	return type;
    }

    public void setType(Type type) {
	this.type = type;
    }

    @JoinColumn(nullable = false)
    @NotNull(message = "{image.required}")
    @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true)
    public Image getImage() {
	return image;
    }

    public void setImage(Image image) {
	this.image = image;
    }

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
    public List<Section> getSubSections() {
	return this.subSections;
    }

    public void setSubSections(List<Section> subSections) {
	this.subSections = subSections;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    public List<Item> getItems() {
	return this.items;
    }

    public void setItems(List<Item> items) {
	this.items = items;
    }

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    public List<Description> getDescription() {
	return description;
    }

    public void setDescription(List<Description> description) {
	this.description = description;
    }
}

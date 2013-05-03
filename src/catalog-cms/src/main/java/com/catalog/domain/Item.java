package com.catalog.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Item extends GenericObject {

    private String name;
    private Type type;
    private Image image;
    private List<Description> description;

    public Item() {
    }

    public Item(String name, Type type, Image image) {
	super();
	this.name = name;
	this.type = type;
	this.image = image;
    }

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "{item.name.not_empty}")
    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Enumerated
    @Column(name = "type", nullable = false)
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
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    public List<Description> getDescription() {
	return description;
    }

    public void setDescription(List<Description> description) {
	this.description = description;
    }
}

package com.catalog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Catalog extends GenericObject {
    
    private String name;
    private List<Section> sections = new ArrayList<>(0);
    private List<Value> values = new ArrayList<>(0);

    public Catalog() {
    }

    public Catalog(String name) {
	this.name = name;
    }

    @NotEmpty(message = "{catalog.name.not_empty}")
    @Column(nullable = false)
    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "catalog", orphanRemoval = true)
    public List<Section> getSections() {
	return this.sections;
    }

    public void setSections(List<Section> sections) {
	this.sections = sections;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "catalog", orphanRemoval = true)
    public List<Value> getValues() {
	return values;
    }

    public void setValues(List<Value> values) {
	this.values = values;
    }
}

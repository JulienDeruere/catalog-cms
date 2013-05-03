package com.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "id" })
@Table(uniqueConstraints = @UniqueConstraint(name = "kc_unique", columnNames = { "sr_key", "catalog" }))
public class Value extends GenericObject {

    private String key;
    private Catalog catalog;

    public Value() {
    }

    public Value(String key) {
	super();
	this.key = key;
    }

    @Column(name = "sr_key", nullable = false)
    @NotEmpty(message = "{value.key.not_empty}")
    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Catalog getCatalog() {
	return catalog;
    }

    public void setCatalog(Catalog catalog) {
	this.catalog = catalog;
    }
}

package com.catalog.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SuppressWarnings("serial")
public class Description extends GenericObject {

    public static final String SEPARATOR = "&&";

    private Value value;
    private List<String> description;

    public Description() {
    }

    public Description(Value value, List<String> description) {
	super();
	this.value = value;
	this.description = description;
    }

    public Description(Value value, String description) {
	super();
	this.value = value;
	this.setDescription(description);
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Value getValue() {
	return value;
    }

    public void setValue(Value value) {
	this.value = value;
    }

    @Lob
    @OrderColumn
    @Column(length = 512)
    @Fetch(FetchMode.SUBSELECT)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "descriptions", joinColumns = @JoinColumn(name = "id"))
    public List<String> getDescription() {
	return description;
    }

    public void setDescription(List<String> description) {
	this.description = description;
    }

    public String descriptionAsString() {
	StringBuilder builder = new StringBuilder();
	boolean isFirst = true;
	for (String desc : description) {
	    if (!isFirst)
		builder.append(SEPARATOR);
	    builder.append(desc);
	    isFirst = false;
	}
	return builder.toString();
    }

    public void setDescription(String description) {
	String[] array = description.split(SEPARATOR);
	this.description = Arrays.asList(array);
    }
}

package com.catalog.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class GenericObject implements Serializable {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || !(obj instanceof GenericObject))
	    return false;
	Long objId = ((GenericObject) obj).getId();
	if (id == null || objId == null)
	    return false;
	return id.equals(objId);
    }
}
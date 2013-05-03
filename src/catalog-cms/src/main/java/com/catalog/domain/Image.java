package com.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "id" })
public class Image extends GenericObject {

    private Type type;
    private String mimeType;
    private String extention;
    private byte[] contents;

    public Image() {
    }

    public Image(Type type, String extention, byte[] contents) {
	super();
	this.type = type;
	this.extention = extention;
	this.contents = contents;
    }

    @Enumerated
    @Column(nullable = false)
    public Type getType() {
	return type;
    }

    public void setType(Type type) {
	this.type = type;
    }

    @Column(nullable = false)
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Column(nullable = false)
    @NotEmpty(message = "{image.required}")
    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Lob
    @JsonIgnore
    @Column(nullable = false)
    public byte[] getContents() {
	return contents;
    }

    public void setContents(byte[] contents) {
	this.contents = contents;
    }
}

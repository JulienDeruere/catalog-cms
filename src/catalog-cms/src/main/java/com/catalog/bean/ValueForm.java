package com.catalog.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.catalog.domain.Catalog;
import com.catalog.domain.Description;
import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.catalog.domain.Value;
import com.catalog.services.ValueService;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "valueForm")
public class ValueForm implements Serializable {

    private String description;
    private Value selectedValue = new Value();
    private List<Value> values = new ArrayList<>();
    private List<Description> descriptions = new ArrayList<>();

    @ManagedProperty("#{valueService}")
    private ValueService valueService;

    public void init(Catalog catalog) {
	values = catalog.getValues();
	descriptions = new ArrayList<>();
    }

    public void init(Catalog catalog, Section section) {
	values = catalog.getValues();
	descriptions = section.getDescription();
    }

    public void init(Catalog catalog, Item item) {
	values = catalog.getValues();
	descriptions = item.getDescription();
    }

    public void add(Catalog catalog) {
	FacesContext context = FacesContext.getCurrentInstance();
	selectedValue.setCatalog(catalog);
	if (selectedValue.getId() == null) {
	    valueService.add(selectedValue);
	    catalog.getValues().add(selectedValue);
	    context.addMessage(null, new FacesMessage("Valeur ajoutée"));
	}
	
	Description desc = valueService.findDescription(descriptions, selectedValue);
	if (desc == null) {
	    desc = new Description(selectedValue, description);
	    descriptions.add(desc);
	} else
	    desc.setDescription(description);

	selectedValue = new Value();
	description = new String();
	context.addMessage(null, new FacesMessage("Description associée"));
   }

    public void handleSelect() {
	description = valueService.findDescriptionAsString(descriptions, selectedValue);
    }

    public Value getSelectedValue() {
	return selectedValue;
    }

    public void setSelectedValue(Value selectedValue) {
	this.selectedValue = selectedValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Value> getValues() {
	return values;
    }

    public void setValues(List<Value> values) {
	this.values = values;
    }

    public List<Description> getDescriptions() {
	return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
	this.descriptions = descriptions;
    }

    public void setValueService(ValueService valueService) {
	this.valueService = valueService;
    }
}

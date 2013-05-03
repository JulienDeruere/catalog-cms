package com.catalog.util.converter;

import com.catalog.domain.Description;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Arrays;
import java.util.List;

@FacesConverter("description")
public class DescriptionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<String> result = Arrays.asList(value.split(Description.SEPARATOR));
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        StringBuilder builder = new StringBuilder();
        for (String desc : (List<String>) value)
            builder.append(desc).append(Description.SEPARATOR);
        return builder.toString();
    }
}
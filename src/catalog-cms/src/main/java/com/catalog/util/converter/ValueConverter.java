package com.catalog.util.converter;

import com.catalog.domain.Value;
import com.catalog.services.ValueService;
import com.catalog.util.Registry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;

@FacesConverter("value")
public class ValueConverter implements Converter {

    public static final String VALUE_NO_ID = "value_no_id_";

    private ValueService valueService = (ValueService) Registry.getService("valueService");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String id = request.getParameter(component.getClientId() + "_input");
        try {
            Value valueObj = valueService.get(Long.parseLong(id));
            if (!value.equals(valueObj.getKey()))
                return new Value(value);
            return valueObj;
        } catch (NumberFormatException ex) {
            return new Value(value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Value aValue = (Value) value;
            if (aValue.getId() != null)
                return Long.toString(aValue.getId());
        }
        return "";
    }
}
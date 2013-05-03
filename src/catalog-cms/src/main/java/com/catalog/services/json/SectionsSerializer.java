package com.catalog.services.json;

import com.catalog.domain.Catalog;
import com.catalog.domain.Description;
import com.catalog.domain.Section;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SectionsSerializer extends StdSerializer<Catalog> {
    
    public SectionsSerializer() {
 	super(Catalog.class, true);
     }

    @Override
    public void serialize(Catalog catalog, JsonGenerator jgen, SerializerProvider provider) throws IOException,
	    JsonGenerationException {
	jgen.writeStartArray();

	for (Section section : catalog.getSections()) {
	    jgen.writeStartObject();
	    jgen.writeStringField("id", "s_" + String.valueOf(section.getId()));
	    jgen.writeStringField("title", section.getName());
	    jgen.writeObjectField("type", section.getType());
	    
	    String imagePath = "s_image_" + section.getId() + "." + section.getImage().getExtention();

	    jgen.writeObjectFieldStart("image");
	    jgen.writeObjectField("type", section.getImage().getType());
	    jgen.writeObjectField("path", imagePath);
	    jgen.writeEndObject();

	    jgen.writeObjectFieldStart("values");
		jgen.writeStringField("productName", section.getName());
		jgen.writeStringField("imageFullPath", imagePath);
	    for (Description description : section.getDescription()) {
		if (description.getDescription().size() > 1) {
		    jgen.writeArrayFieldStart(description.getValue().getKey());
		    for (String desc : description.getDescription())
			jgen.writeString(desc);
		    jgen.writeEndArray();
		} else
		    jgen.writeStringField(description.getValue().getKey(),
			    (description.getDescription().size() == 0) ? "" : description.getDescription().get(0));
	    }
	    jgen.writeEndObject();
	    jgen.writeEndObject();
	}
	jgen.writeEndArray();
    }
}

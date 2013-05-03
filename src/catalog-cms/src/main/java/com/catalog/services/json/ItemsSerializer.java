package com.catalog.services.json;

import com.catalog.domain.Item;
import com.catalog.domain.Section;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ItemsSerializer extends StdSerializer<Section> {
    
    public ItemsSerializer() {
 	super(Section.class, true);
     }

    @Override
    public void serialize(Section section, JsonGenerator jgen, SerializerProvider provider) throws IOException,
	    JsonProcessingException {
	jgen.writeStartArray();
	for (Item item : section.getItems()) {
	    String imagePath = "i_image_" + item.getId() + "." + item.getImage().getExtention();
	    
	    jgen.writeStartObject();
	    jgen.writeStringField("id", "i_" + String.valueOf(item.getId()));
	    jgen.writeObjectField("title", item.getName());
	    jgen.writeObjectField("type", item.getType());
	    jgen.writeObjectFieldStart("image");
	    jgen.writeObjectField("type", section.getImage().getType());
	    jgen.writeObjectField("path", imagePath);
	    jgen.writeEndObject();
	    jgen.writeEndObject();
	}
	jgen.writeEndArray();
    }
}

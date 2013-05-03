package com.catalog.services.json;

import com.catalog.domain.Description;
import com.catalog.domain.Item;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ItemSerializer extends StdSerializer<Item> {
    
    public ItemSerializer() {
	super(Item.class, true);
    }

    @Override
    public void serialize(Item item, JsonGenerator jgen, SerializerProvider provider) throws IOException,
	    JsonProcessingException {
	jgen.writeStartArray();

	jgen.writeStartObject();
	jgen.writeStringField("id", "i_" + String.valueOf(item.getId()));
	jgen.writeObjectField("title", item.getName());
	jgen.writeObjectField("type", item.getType());
	
	String imagePath = "i_image_" + item.getId() + "." + item.getImage().getExtention();

	jgen.writeObjectFieldStart("image");
	jgen.writeObjectField("type", item.getImage().getType());
	jgen.writeObjectField("path", imagePath);
	jgen.writeEndObject();

	jgen.writeObjectFieldStart("values");
	jgen.writeStringField("productName", item.getName());
	jgen.writeStringField("imageFullPath", imagePath);
	for (Description description : item.getDescription()) {
	    if (description.getDescription().size() > 1) {
		jgen.writeArrayFieldStart(description.getValue().getKey());
		for (String desc : description.getDescription())
		    jgen.writeString(desc);
		jgen.writeEndArray();
	    } else
		jgen.writeStringField(description.getValue().getKey(), (description.getDescription().size() == 0) ? ""
			: description.getDescription().get(0));
	}
	jgen.writeEndObject();

	jgen.writeEndObject();
	jgen.writeEndArray();
    }
}

package com.catalog.services.json;

import com.catalog.domain.Type;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TypeSerializer extends StdSerializer<Type> {

    public TypeSerializer() {
	super(Type.class, true);
    }

    @Override
    public void serialize(Type value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
	    JsonGenerationException {
	jgen.writeString(Integer.toString(value.code()));
    }
}

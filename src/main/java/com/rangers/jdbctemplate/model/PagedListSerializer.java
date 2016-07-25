
package com.rangers.jdbctemplate.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("rawtypes")
public class PagedListSerializer extends JsonSerializer<PagedList> {

  @Override
  public void serialize(PagedList value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    gen.writeStartObject();
    gen.writeNumberField("totalRecords", value.getTotalRecords());
    gen.writeObjectField("list", value.getList());
    gen.writeEndObject();
  }

  @Override
  public Class<PagedList> handledType() {
    return PagedList.class;
  }
}

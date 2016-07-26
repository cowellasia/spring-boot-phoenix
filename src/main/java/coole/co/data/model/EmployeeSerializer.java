package coole.co.data.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EmployeeSerializer extends JsonSerializer<Employee> {

  @Override
  public void serialize(Employee value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    gen.writeStartObject();
    gen.writeNumberField("employeeId", value.getId());
    gen.writeStringField("employeeName", value.getName());
    gen.writeEndObject();
  }
  
  @Override
  public Class<Employee> handledType() { return Employee.class; }
}

package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.InstantConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final InstantConverter converter = new InstantConverter();

    public DateAdapter() {
    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return Date.from(converter.deserialize(jsonElement, type, jsonDeserializationContext));
        } catch (Exception e) {
            return parse(jsonElement.getAsString());
        }
    }

    private Date parse(String v) {
        try {
            return new Date(v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonElement serialize(Date offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime.toInstant(), type, jsonSerializationContext);
    }
}

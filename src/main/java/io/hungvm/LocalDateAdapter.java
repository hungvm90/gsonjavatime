package io.hungvm;

import com.fatboyindustrial.gsonjavatime.LocalDateConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private final LocalDateConverter converter = new LocalDateConverter();

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            JsonObject date = jsonElement.getAsJsonObject();
            return LocalDate.of(date.get("year").getAsInt(), date.get("month").getAsInt(), date.get("day").getAsInt());
        }
    }

    @Override
    public JsonElement serialize(LocalDate offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

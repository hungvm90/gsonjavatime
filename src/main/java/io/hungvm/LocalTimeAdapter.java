package io.hungvm;

import com.fatboyindustrial.gsonjavatime.LocalTimeConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalTime;

public class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    private final LocalTimeConverter converter = new LocalTimeConverter();

    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            JsonObject time = jsonElement.getAsJsonObject();
            return LocalTime.of(time.get("hour").getAsInt(), time.get("minute").getAsInt(), time.get("second").getAsInt(), time.get("nano").getAsInt());
        }
    }

    @Override
    public JsonElement serialize(LocalTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

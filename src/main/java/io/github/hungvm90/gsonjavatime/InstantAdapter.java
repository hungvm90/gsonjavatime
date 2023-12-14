package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.InstantConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;

public class InstantAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

    private final InstantConverter converter = new InstantConverter();

    @Override
    public Instant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            long seconds = jsonElement.getAsJsonObject().get("seconds").getAsLong();
            long nanos = jsonElement.getAsJsonObject().get("nanos").getAsLong();
            return Instant.ofEpochSecond(seconds, nanos);
        }
    }

    @Override
    public JsonElement serialize(Instant offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

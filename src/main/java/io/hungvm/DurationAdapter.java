package io.hungvm;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Duration;

public class DurationAdapter implements JsonSerializer<Duration>, JsonDeserializer<Duration> {


    @Override
    public Duration deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            if (json == null) {
                return null;
            }

            if (json.isJsonNull()) {
                return null;
            }

            final String zoneIdentifier = json.getAsString();
            if (zoneIdentifier == null || zoneIdentifier.isEmpty()) {
                return null;
            }
            return Duration.parse(json.getAsString());
        } catch (Exception e) {
            JsonObject dateTime = json.getAsJsonObject();
            return Duration.ofSeconds(dateTime.get("seconds").getAsLong(), dateTime.get("nanos").getAsLong());
        }
    }

    @Override
    public JsonElement serialize(Duration jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        if (jsonElement == null) {
            return null;
        }
        return new JsonPrimitive(jsonElement.toString());
    }
}

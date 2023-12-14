package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.ZoneIdConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class ZoneIdAdapter implements JsonSerializer<ZoneId>, JsonDeserializer<ZoneId> {

    private final ZoneIdConverter converter = new ZoneIdConverter();

    @Override
    public ZoneId deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            var zone = jsonElement.getAsJsonObject();
            if (zone.has("id")) {
                return ZoneId.of(jsonElement.getAsJsonObject().get("id").getAsString());
            } else {
                return ZoneId.of(ZoneOffset.ofTotalSeconds(zone.get("totalSeconds").getAsInt()).getId());
            }

        }
    }



    @Override
    public JsonElement serialize(ZoneId offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

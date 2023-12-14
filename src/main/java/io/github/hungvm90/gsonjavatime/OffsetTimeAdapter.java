package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.OffsetTimeConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

public class OffsetTimeAdapter implements JsonSerializer<OffsetTime>, JsonDeserializer<OffsetTime> {

    private final OffsetTimeConverter converter = new OffsetTimeConverter();

    @Override
    public OffsetTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            JsonObject dateTime = jsonElement.getAsJsonObject();
            JsonObject time = dateTime.getAsJsonObject("time");
            JsonObject offset = dateTime.getAsJsonObject("offset");
            LocalTime localTime = LocalTime.of(time.get("hour").getAsInt(), time.get("minute").getAsInt(), time.get("second").getAsInt(), time.get("nano").getAsInt());
            ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(offset.get("totalSeconds").getAsInt());
            return OffsetTime.of(localTime, zoneOffset);
        }
    }

    @Override
    public JsonElement serialize(OffsetTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

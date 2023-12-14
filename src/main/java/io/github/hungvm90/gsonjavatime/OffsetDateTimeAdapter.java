package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.OffsetDateTimeConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    private final OffsetDateTimeConverter converter = new OffsetDateTimeConverter();

    @Override
    public OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            JsonObject dateTime = jsonElement.getAsJsonObject().getAsJsonObject("dateTime");
            JsonObject date = dateTime.getAsJsonObject("date");
            JsonObject time = dateTime.getAsJsonObject("time");
            JsonObject offset = jsonElement.getAsJsonObject().getAsJsonObject("offset");
            LocalDate localDate = LocalDate.of(date.get("year").getAsInt(), date.get("month").getAsInt(), date.get("day").getAsInt());
            LocalTime localTime = LocalTime.of(time.get("hour").getAsInt(), time.get("minute").getAsInt(), time.get("second").getAsInt(), time.get("nano").getAsInt());
            ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(offset.get("totalSeconds").getAsInt());
            return OffsetDateTime.of(localDate, localTime, zoneOffset);
        }
    }

    @Override
    public JsonElement serialize(OffsetDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

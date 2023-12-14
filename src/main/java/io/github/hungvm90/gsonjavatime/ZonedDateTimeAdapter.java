package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.ZonedDateTimeConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.*;

public class ZonedDateTimeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    private final ZonedDateTimeConverter converter = new ZonedDateTimeConverter();

    @Override
    public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            JsonObject dateTime = jsonElement.getAsJsonObject().getAsJsonObject("dateTime");
            JsonObject date = dateTime.getAsJsonObject("date");
            JsonObject time = dateTime.getAsJsonObject("time");
            JsonObject zone = jsonElement.getAsJsonObject().getAsJsonObject("zone");
            LocalDate localDate = LocalDate.of(date.get("year").getAsInt(), date.get("month").getAsInt(), date.get("day").getAsInt());
            LocalTime localTime = LocalTime.of(time.get("hour").getAsInt(), time.get("minute").getAsInt(), time.get("second").getAsInt(), time.get("nano").getAsInt());
            return ZonedDateTime.of(localDate, localTime, parseZone(zone));
        }
    }

    private ZoneId parseZone(JsonObject zone) {
        if (zone.has("id")) {
            return ZoneId.of(zone.get("id").getAsString());
        } else {
            return ZoneId.of(ZoneOffset.ofTotalSeconds(zone.get("totalSeconds").getAsInt()).getId());
        }
    }

    @Override
    public JsonElement serialize(ZonedDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

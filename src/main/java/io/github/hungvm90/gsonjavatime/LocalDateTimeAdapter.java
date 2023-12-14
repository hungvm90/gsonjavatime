package io.github.hungvm90.gsonjavatime;

import com.fatboyindustrial.gsonjavatime.LocalDateTimeConverter;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private final LocalDateTimeConverter converter = new LocalDateTimeConverter();

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return converter.deserialize(jsonElement, type, jsonDeserializationContext);
        } catch (UnsupportedOperationException e) {
            JsonObject dateTime = jsonElement.getAsJsonObject();
            JsonObject date = dateTime.getAsJsonObject("date");
            JsonObject time = dateTime.getAsJsonObject("time");
            LocalDate localDate = LocalDate.of(date.get("year").getAsInt(), date.get("month").getAsInt(), date.get("day").getAsInt());
            LocalTime localTime = LocalTime.of(time.get("hour").getAsInt(), time.get("minute").getAsInt(), time.get("second").getAsInt(), time.get("nano").getAsInt());
            return LocalDateTime.of(localDate, localTime);
        }
    }

    @Override
    public JsonElement serialize(LocalDateTime offsetDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return converter.serialize(offsetDateTime, type, jsonSerializationContext);
    }
}

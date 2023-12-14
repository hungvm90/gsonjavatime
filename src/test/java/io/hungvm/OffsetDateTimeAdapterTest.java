package io.hungvm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.*;

public class OffsetDateTimeAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = OffsetDateTime.of(LocalDateTime.of(2023, Month.DECEMBER, 14, 13, 30, 21, 1230000), ZoneOffset.ofHours(1));
        String s = gson.toJson(t);
        assertEquals("\"2023-12-14T13:30:21.00123+01:00\"", s);
        var getBack = gson.fromJson(s, OffsetDateTime.class);
        assertEquals(t, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = OffsetDateTime.now();
        var getBack = gson.fromJson(oldGson.toJson(t), OffsetDateTime.class);
        assertEquals(t, getBack);
    }
}
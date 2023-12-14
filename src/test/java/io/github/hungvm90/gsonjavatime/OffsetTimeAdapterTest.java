package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.*;

public class OffsetTimeAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = OffsetTime.of(LocalTime.of(13, 30, 21, 1230000), ZoneOffset.ofHours(1));
        String s = gson.toJson(t);
        assertEquals("\"13:30:21.00123+01:00\"", s);
        var getBack = gson.fromJson(s, OffsetTime.class);
        assertEquals(t, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = OffsetTime.now();
        var getBack = gson.fromJson(oldGson.toJson(t), OffsetTime.class);
        assertEquals(t, getBack);
    }
}
package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class LocalDateTimeAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = LocalDateTime.of(2023, Month.DECEMBER, 14, 13, 30, 21);
        String s = gson.toJson(t);
        assertEquals("\"2023-12-14T13:30:21\"", s);
        var getBack = gson.fromJson(s, LocalDateTime.class);
        assertEquals(t, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = LocalDateTime.of(2023, Month.DECEMBER, 14, 13, 30, 21);
        var getBack = gson.fromJson(oldGson.toJson(t), LocalDateTime.class);
        assertEquals(t, getBack);
    }
}
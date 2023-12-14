package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class LocalDateAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = LocalDate.of(2023, Month.DECEMBER, 14);
        String s = gson.toJson(t);
        assertEquals("\"2023-12-14\"", s);
        var getBack = gson.fromJson(s, LocalDate.class);
        assertEquals(t, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = LocalDate.of(2023, Month.DECEMBER, 14);
        var getBack = gson.fromJson(oldGson.toJson(t), LocalDate.class);
        assertEquals(t, getBack);
    }
}
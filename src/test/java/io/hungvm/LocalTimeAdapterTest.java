package io.hungvm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class LocalTimeAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = LocalTime.of(13, 30, 21);
        String s = gson.toJson(t);
        assertEquals("\"13:30:21\"", s);
        var getBack = gson.fromJson(s, LocalTime.class);
        assertEquals(t, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        var t = LocalTime.of(13, 30, 21);
        var getBack = gson.fromJson(oldGson.toJson(t), LocalTime.class);
        assertEquals(t, getBack);
    }
}
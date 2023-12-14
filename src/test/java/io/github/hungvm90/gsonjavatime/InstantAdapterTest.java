package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class InstantAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        Instant instant = Instant.now();
        String s = gson.toJson(instant);
        Instant getBack = gson.fromJson(s, Instant.class);
        assertEquals(instant, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        Instant instant = Instant.now();
        Instant getBack = gson.fromJson(oldGson.toJson(instant), Instant.class);
        assertEquals(instant, getBack);
    }
}
package io.hungvm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class DurationAdapterTest {

    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        Duration duration = Duration.ofSeconds(300);
        String s = gson.toJson(duration);
        Duration getBack = gson.fromJson(s, Duration.class);
        assertEquals(duration, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        Duration duration = Duration.ofSeconds(300);
        Duration getBack = gson.fromJson(oldGson.toJson(duration), Duration.class);
        assertEquals(duration, getBack);
    }

}
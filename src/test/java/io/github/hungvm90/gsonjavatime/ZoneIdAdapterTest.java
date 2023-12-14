package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.*;

public class ZoneIdAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        ZoneId t = ZoneId.of("Asia/Ho_Chi_Minh");
        String s = gson.toJson(t);
        assertEquals("\"Asia/Ho_Chi_Minh\"", s);
        var getBack = gson.fromJson(s, ZoneId.class);
        assertEquals(t, getBack);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerAll(new GsonBuilder()).create();
        ZoneId t = ZoneId.of("Asia/Ho_Chi_Minh");
        var getBack = gson.fromJson(oldGson.toJson(t), ZoneId.class);
        assertEquals(t, getBack);
    }
}
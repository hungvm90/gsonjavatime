package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateAdapterTest {
    @Test
    public void testSerialisation() {
        Gson gson = JavaTimeConverters.registerDate(new GsonBuilder()).create();
        Date date = new Date(2023 - 1900, Calendar.NOVEMBER, 16, 22, 1, 1);
        String s = gson.toJson(date);
        Date getBack = gson.fromJson(s, Date.class);
        assertEquals(date.getTime() / 1000, getBack.getTime() / 1000);
    }

    @Test
    public void testCompatible() {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerDate(new GsonBuilder()).create();
        Date date = new Date(2023 - 1900, Calendar.NOVEMBER, 16, 22, 13, 15);
        Date getBack = gson.fromJson(oldGson.toJson(date), Date.class);
        assertEquals(date.getTime() / 1000, getBack.getTime() / 1000);
    }
}
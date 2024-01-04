package io.github.hungvm90.gsonjavatime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Test
    public void testThreadSafe() throws Exception {
        Gson oldGson = new Gson();
        Gson gson = JavaTimeConverters.registerDate(new GsonBuilder()).create();
        Date date = new Date(2023 - 1900, Calendar.NOVEMBER, 16, 22, 13, 15);
        String data = oldGson.toJson(date);
        AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                Date getBack = gson.fromJson(data, Date.class);
                assertEquals(date.getTime() / 1000, getBack.getTime() / 1000);
                count.incrementAndGet();
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        assertEquals(1000, count.get());
    }
}
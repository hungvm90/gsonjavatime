package io.hungvm;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.util.Date;

public class JavaTimeConverters {
    public static GsonBuilder registerAll(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Date.class, new DateAdapter());
            builder.registerTypeAdapter(Converters.LOCAL_DATE_TYPE, new LocalDateAdapter());
            builder.registerTypeAdapter(Converters.LOCAL_DATE_TIME_TYPE, new LocalDateTimeAdapter());
            builder.registerTypeAdapter(Converters.LOCAL_TIME_TYPE, new LocalTimeAdapter());
            builder.registerTypeAdapter(Converters.OFFSET_DATE_TIME_TYPE, new OffsetDateTimeAdapter());
            builder.registerTypeAdapter(Converters.ZONED_DATE_TIME_TYPE, new ZonedDateTimeAdapter());
            builder.registerTypeAdapter(Converters.INSTANT_TYPE, new InstantAdapter());
            builder.registerTypeAdapter(Converters.ZONE_ID_TYPE, new ZoneIdAdapter());
            builder.registerTypeAdapter(Converters.OFFSET_TIME_TYPE, new OffsetTimeAdapter());
            builder.registerTypeAdapter(Duration.class, new DurationAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerDate(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Date.class, new DateAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerLocalDate(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.LOCAL_DATE_TYPE, new LocalDateAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerLocalDateTime(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.LOCAL_DATE_TIME_TYPE, new LocalDateTimeAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerLocalTime(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.LOCAL_TIME_TYPE, new LocalTimeAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerOffsetDateTime(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.OFFSET_DATE_TIME_TYPE, new OffsetDateTimeAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerZonedDateTime(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.ZONED_DATE_TIME_TYPE, new ZonedDateTimeAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerInstant(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.INSTANT_TYPE, new InstantAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerZoneId(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.ZONE_ID_TYPE, new ZoneIdAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerOffsetTime(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Converters.OFFSET_TIME_TYPE, new OffsetTimeAdapter());
            return builder;
        }
    }

    public static GsonBuilder registerDuration(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        } else {
            builder.registerTypeAdapter(Duration.class, new DurationAdapter());
            return builder;
        }
    }
}

package lab11.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class EventLog {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private EventLog() {
    }

    public static void log(String source, String event) {
        System.out.printf("[%s] [%s] %s%n", LocalTime.now().format(TIME_FORMAT), source, event);
    }
}

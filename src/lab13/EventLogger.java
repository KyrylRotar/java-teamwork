package lab13;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class EventLogger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private EventLogger() {
    }

    public static void log(String source, String event) {
        System.out.printf("[%s] [%s] %s%n", LocalTime.now().format(FORMATTER), source, event);
    }
}

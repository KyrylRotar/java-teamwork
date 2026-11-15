package lab11.task4;

import lab11.EventLogger;

public class ProjectorSystem {
    public void configure(String format) {
        EventLogger.log("ProjectorSystem", "Налаштування лазерного проектора під формат: " + format);
    }
}
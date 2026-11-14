package lab10.task3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task3Runner {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        runScenario();
    }

    public static void runScenario() {
        log("=== ПОЧАТОК ДЕМОНСТРАЦІЇ: ЗАВДАННЯ 3 (ABSTRACT FACTORY) ===");

        HallController hall = new HallController("Зал №1");

        hall.startScreening("Інтерстеллар", new Format2DFactory());
        System.out.println();
        hall.startScreening("Аватар: Шлях води", new Format3DFactory());
        System.out.println();
        hall.startScreening("Оппенгеймер", new DolbyAtmosFactory());
        System.out.println();

        log("--- Додавання формату IMAX без зміни коду запуску сеансу ---");
        hall.startScreening("Дюна: Частина друга", new ImaxFactory());

        log("=== ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО ===");
    }

    private static void log(String message) {
        System.out.printf("[%s] [Раннер Завдання 3] %s%n", LocalTime.now().format(TIME_FORMAT), message);
    }
}
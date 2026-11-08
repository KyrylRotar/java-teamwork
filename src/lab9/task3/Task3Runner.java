package lab9.task3;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Task3Runner {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        runScenario();
    }

    public static void runScenario() throws InterruptedException {
        log("=== ПОЧАТОК ДЕМОНСТРАЦІЇ: ЗАВДАННЯ 3 (OBJECT POOL) ===");

        // Ініціалізуємо пул із лімітом на 4 пари
        GlassesPool pool = new GlassesPool(4);

        // 6 глядачів приходять на 3D-сеанс
        String[] seats = {"5-7", "5-8", "6-1", "6-2", "7-5", "7-6"};
        Map<String, Glasses> activeDistributions = new HashMap<>();

        log("--- Сценарій 1: 6 глядачів одночасно запитують окуляри ---");
        for (String seat : seats) {
            Glasses g = pool.acquireGlasses(seat);
            if (g != null) {
                activeDistributions.put(seat, g);
            }
            Thread.sleep(100);
        }

        Thread.sleep(500);
        log("--- Сценарій 2: Завершення попереднього сеансу та повернення 2-х пар ---");

        // Повертаємо окуляри перших двох глядачів (місця 5-7 та 5-8)
        Glasses g1 = activeDistributions.remove("5-7");
        Glasses g2 = activeDistributions.remove("5-8");

        pool.releaseGlasses(g1);
        Thread.sleep(100);
        pool.releaseGlasses(g2);

        Thread.sleep(300);
        log("--- Сценарій 3: Завершення сеансу та виведення статистики ---");

        // Виводимо фінальну статистику
        pool.printStatistics();

        log("=== ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО ===");
    }

    private static void log(String message) {
        System.out.printf("[%s] [Раннер] %s%n", LocalTime.now().format(TIME_FORMAT), message);
    }
}
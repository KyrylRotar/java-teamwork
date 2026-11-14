package lab11.task3;

import lab11.EventLogger;

public class Task3Runner {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        EventLogger.log("Task3Runner", "=== ДЕМОНСТРАЦІЯ ЗАВДАННЯ 3: МОДИФІКАЦІЇ СЕАНСУ (DECORATOR) ===");

        // 1. Базовий сеанс
        EventLogger.log("Task3Runner", "--- Крок 1: Базовий сеанс ---");
        MovieSession basicSession = new BaseSession("Дюна: Частина друга", "IMAX", 200.0);
        String desc1 = basicSession.getDescription();
        double price1 = basicSession.getPrice();
        EventLogger.log("Task3Runner", "Результат: " + desc1 + " | Ціна: " + price1 + " грн\n");

        // 2. Сеанс 3D із субтитрами
        EventLogger.log("Task3Runner", "--- Крок 2: Сеанс 3D із субтитрами ---");
        MovieSession session3DSub = new SubtitlesDecorator(
                new ThreeDDecorator(
                        new BaseSession("Аватар: Шлях води", "Червоний", 180.0)
                )
        );
        String desc2 = session3DSub.getDescription();
        double price2 = session3DSub.getPrice();
        EventLogger.log("Task3Runner", "Результат: " + desc2 + " | Ціна: " + price2 + " грн\n");

        // 3. «Тихий сеанс» зі знижкою вихідного дня
        EventLogger.log("Task3Runner", "--- Крок 3: «Тихий сеанс» зі знижкою вихідного дня ---");
        MovieSession quietWeekendSession = new WeekendDiscountDecorator(
                new QuietSessionDecorator(
                        new BaseSession("Думками навиворіт 2", "Синій", 160.0)
                )
        );
        String desc3 = quietWeekendSession.getDescription();
        double price3 = quietWeekendSession.getPrice();
        EventLogger.log("Task3Runner", "Результат: " + desc3 + " | Ціна: " + price3 + " грн");

        EventLogger.log("Task3Runner", "=== ДЕМОНСТРАЦІЮ ЗАВДАННЯ 3 ЗАВЕРШЕНО ===");
    }
}
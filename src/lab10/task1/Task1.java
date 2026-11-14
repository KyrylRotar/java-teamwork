package lab10.task1;

import java.time.LocalDateTime;

/** Демонстрація Builder для замовлень кінотеатру «Кадр». */
public final class Task1 {
    private static int minute;

    private Task1() {
    }

    public static void main(String[] args) {
        MovieSession session = new MovieSession(
                "Пригоди у зоряному місті",
                LocalDateTime.of(2026, 11, 14, 19, 30)
        );
        demonstrateCustomOrder(session);
        demonstrateComboForTwo(session);
        demonstrateOrderWithoutSeats(session);
    }

    private static void demonstrateCustomOrder(MovieSession session) {
        log("Каса", "Почато формування замовлення на два місця з додатками.");
        CinemaOrder order = new CinemaOrderBuilder()
                .setOrderName("Замовлення глядача")
                .setSession(session)
                .addSeat("A7")
                .addSeat("A8")
                .addPopcorn(1)
                .addDrinks(2)
                .applyPromoCode("KADR10")
                .build();
        log("Система", "Сформовано: " + order);
    }

    private static void demonstrateComboForTwo(MovieSession session) {
        log("Каса", "Формується типове замовлення «Комбо на двох».");
        CinemaOrder order = new CinemaOrderDirector().createComboForTwo(session);
        log("Система", "Сформовано: " + order);
    }

    private static void demonstrateOrderWithoutSeats(MovieSession session) {
        log("Каса", "Спроба завершити формування замовлення без місць.");
        try {
            new CinemaOrderBuilder()
                    .setSession(session)
                    .applyPromoCode("KADR10")
                    .build();
        } catch (IllegalStateException exception) {
            log("Система", exception.getMessage());
        }
    }

    private static void log(String source, String event) {
        int totalMinutes = 19 * 60 + 30 + minute;
        int hour = totalMinutes / 60 % 24;
        int minuteOfHour = totalMinutes % 60;
        System.out.printf("[%02d:%02d] [%s] %s%n", hour, minuteOfHour, source, event);
        minute++;
    }
}

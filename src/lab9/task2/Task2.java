package lab9.task2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Демонстрація Prototype для варіанта 4 — кінотеатр «Кадр». */
public final class Task2 {
    private Task2() {
    }

    public static void main(String[] args) {
        ScreeningCatalog catalog = new ScreeningCatalog();
        ScreeningPrototype prototype = catalog.getOrCreatePrototype("Вечірній фільм", 2);
        if (prototype != catalog.getOrCreatePrototype("Вечірній фільм", 2)) {
            throw new AssertionError("Для фільму в залі має повторно використовуватися той самий прототип.");
        }

        List<Screening> screenings = new ArrayList<>();
        screenings.add(prototype.createScreening(LocalDateTime.of(2026, 11, 6, 17, 0)));
        screenings.add(prototype.createScreening(LocalDateTime.of(2026, 11, 6, 19, 0)));
        screenings.add(prototype.createScreening(LocalDateTime.of(2026, 11, 6, 21, 0)));
        screenings.add(prototype.createScreening(LocalDateTime.of(2026, 11, 6, 23, 0)));

        Screening pilotScreening = screenings.get(1);
        pilotScreening.changeRowPrice(1, new BigDecimal("99.00"));
        pilotScreening.replaceAdvertisingClip(0, "Рекламний ролик пілоту");
        pilotScreening.sellSeat(new Seat(1, 1));
        pilotScreening.sellSeat(new Seat(1, 2));
        verifyIsolation(prototype, screenings, pilotScreening);

        System.out.println("\nЗразок і змінений сеанс:");
        System.out.println(prototype);
        System.out.println(pilotScreening);
        System.out.println("\nСтворено сеансів: " + screenings.size());
    }

    private static void verifyIsolation(
            ScreeningPrototype prototype,
            List<Screening> screenings,
            Screening changedScreening
    ) {
        if (!new BigDecimal("180.00").equals(prototype.createScreening(changedScreening.getStartsAt()).getRowPrice(1))) {
            throw new AssertionError("Зміна ціни вплинула на прототип.");
        }
        if (!new BigDecimal("180.00").equals(screenings.get(0).getRowPrice(1))) {
            throw new AssertionError("Зміна ціни вплинула на інший сеанс.");
        }
        if (!"Трейлер майбутньої прем'єри".equals(
                prototype.createScreening(changedScreening.getStartsAt())
                        .getAdvertisingBlock().getClips().get(0))) {
            throw new AssertionError("Зміна рекламного ролика вплинула на прототип.");
        }
        if (!"Трейлер майбутньої прем'єри".equals(
                screenings.get(0).getAdvertisingBlock().getClips().get(0))) {
            throw new AssertionError("Зміна рекламного ролика вплинула на інший сеанс.");
        }
        if (prototype.createScreening(changedScreening.getStartsAt())
                .getSeatStatuses().get(new Seat(1, 1)) != SeatStatus.AVAILABLE) {
            throw new AssertionError("Продаж місця вплинув на прототип.");
        }
        if (screenings.get(0).getSeatStatuses().get(new Seat(1, 1)) != SeatStatus.AVAILABLE) {
            throw new AssertionError("Продаж місця вплинув на інший сеанс.");
        }
        if (!new BigDecimal("99.00").equals(changedScreening.getRowPrice(1))
                || changedScreening.getSeatStatuses().get(new Seat(1, 1)) != SeatStatus.SOLD) {
            throw new AssertionError("Зміни не застосовано до цільового сеансу.");
        }
    }
}

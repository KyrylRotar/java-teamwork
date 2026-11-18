package lab12.task2;

import lab12.EventLogger;

import java.util.ArrayList;
import java.util.List;

public final class Task2Runner {
    private static final int SESSION_COUNT = 200;
    private static final int ROW_COUNT = 10;
    private static final int SEATS_PER_ROW = 18;

    private Task2Runner() {
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        EventLogger.log("Task2Runner", "=== ЗАВДАННЯ 2: СХЕМА МІСЦЬ НА СЕАНСАХ (FLYWEIGHT) ===");

        SeatCategoryFactory categoryFactory = new SeatCategoryFactory();
        List<CinemaSeat> allSeats = new ArrayList<>(SESSION_COUNT * ROW_COUNT * SEATS_PER_ROW);
        CinemaSeat firstVipSeat = null;
        CinemaSeat lastVipSeat = null;

        for (int sessionNumber = 1; sessionNumber <= SESSION_COUNT; sessionNumber++) {
            String sessionId = String.format("S-%03d", sessionNumber);
            for (int rowNumber = 1; rowNumber <= ROW_COUNT; rowNumber++) {
                SeatCategoryType categoryType = categoryForRow(rowNumber);
                SeatCategory category = categoryFactory.getCategory(categoryType);
                String row = String.valueOf((char) ('A' + rowNumber - 1));

                for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW; seatNumber++) {
                    CinemaSeat seat = new CinemaSeat(sessionId, row, seatNumber, category);
                    allSeats.add(seat);
                    if (categoryType == SeatCategoryType.VIP && seatNumber == 1) {
                        if (sessionNumber == 1) {
                            firstVipSeat = seat;
                        } else if (sessionNumber == SESSION_COUNT) {
                            lastVipSeat = seat;
                        }
                    }
                }
            }
        }

        if (firstVipSeat == null || lastVipSeat == null) {
            throw new IllegalStateException("Не вдалося створити демонстраційні VIP-місця.");
        }
        firstVipSeat.sell();

        EventLogger.log("Task2Runner", "Створено схем для сеансів: " + SESSION_COUNT);
        EventLogger.log("Task2Runner", "Фактично створено об'єктів місць: " + allSeats.size());
        EventLogger.log("Task2Runner", "Фактично створено об'єктів категорій: "
                + categoryFactory.getCreatedCategoryCount() + " (стандарт, комфорт, VIP)");

        EventLogger.log("Task2Runner", "--- VIP-місця на різних сеансах ---");
        EventLogger.log("Місце", firstVipSeat.getFullDescription());
        EventLogger.log("Місце", lastVipSeat.getFullDescription());
        EventLogger.log("Task2Runner", "Обидва VIP-місця посилаються на той самий об'єкт категорії: "
                + (firstVipSeat.getCategory() == lastVipSeat.getCategory()));

        EventLogger.log("Task2Runner", "=== ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО ===");
    }

    private static SeatCategoryType categoryForRow(int rowNumber) {
        if (rowNumber <= 6) {
            return SeatCategoryType.STANDARD;
        }
        if (rowNumber <= 8) {
            return SeatCategoryType.COMFORT;
        }
        return SeatCategoryType.VIP;
    }
}

package lab9.task3;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Glasses {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String id;
    private String currentSeat;
    private int usageCount;

    public Glasses(String id) {
        this.id = id;
        this.usageCount = 0;
        this.currentSeat = null;
        emulateAcquisitionCost();
    }

    private void emulateAcquisitionCost() {
        log("Закупівля та оптичне калібрування нової пари " + id + "...");
        try {
            Thread.sleep(300); // емуляція дорогої операції
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void assignToSeat(String seat) {
        this.currentSeat = seat;
        this.usageCount++;
    }

    /**
     * Скидання стану перед поверненням у вільний пул
     */
    public void sanitizeAndReset() {
        this.currentSeat = null;
    }

    public String getId() {
        return id;
    }

    public String getCurrentSeat() {
        return currentSeat;
    }

    public int getUsageCount() {
        return usageCount;
    }

    private void log(String message) {
        System.out.printf("[%s] [Окуляри %s] %s%n", LocalTime.now().format(TIME_FORMAT), id, message);
    }
}
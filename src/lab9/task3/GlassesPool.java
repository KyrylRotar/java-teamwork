package lab9.task3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GlassesPool {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final int poolCapacity;

    private final List<Glasses> allCreatedGlasses = new ArrayList<>();
    private final Queue<Glasses> availableGlasses = new LinkedList<>();
    private final Queue<String> waitingQueue = new LinkedList<>();

    public GlassesPool(int capacity) {
        this.poolCapacity = capacity;
        log("Ініціалізація пулу 3D-окулярів (максимум: " + poolCapacity + " пар).");
        for (int i = 1; i <= capacity; i++) {
            Glasses g = new Glasses("G-" + i);
            allCreatedGlasses.add(g);
            availableGlasses.add(g);
        }
        log("Пул готовий до роботи. Усі " + capacity + " пар доступні.");
    }

    public Glasses acquireGlasses(String seat) {
        if (!availableGlasses.isEmpty()) {
            Glasses g = availableGlasses.poll();
            g.assignToSeat(seat);
            log("Окуляри " + g.getId() + " → місце " + seat);
            return g;
        } else {
            waitingQueue.offer(seat);
            log("Усі окуляри зайняті! Глядач на місці " + seat + " доданий у чергу очікування.");
            return null;
        }
    }

    public void releaseGlasses(Glasses g) {
        if (g == null) return;

        g.sanitizeAndReset();
        log(g.getId() + " повернено, продезінфіковано");

        if (!waitingQueue.isEmpty()) {
            String nextSeat = waitingQueue.poll();
            g.assignToSeat(nextSeat);
            log("Окуляри " + g.getId() + " одразу видано з черги → місце " + nextSeat);
        } else {
            availableGlasses.offer(g);
        }
    }

    public int getPoolCapacity() {
        return poolCapacity;
    }

    public void printStatistics() {
        System.out.println("\n================ СТАТИСТИКА ПУЛУ ================");
        System.out.println("Загальна місткість пулу: " + poolCapacity);
        System.out.println("Загальна кількість фізично створених пар: " + allCreatedGlasses.size());
        for (Glasses g : allCreatedGlasses) {
            System.out.printf("• Пара %-4s : кількість видач = %d%n", g.getId(), g.getUsageCount());
        }
        System.out.println("Глядачів у черзі, що не отримали окуляри: " + waitingQueue.size());
        System.out.println("=================================================\n");
    }

    private void log(String message) {
        System.out.printf("[%s] [Пул Окулярів] %s%n", LocalTime.now().format(TIME_FORMAT), message);
    }
}
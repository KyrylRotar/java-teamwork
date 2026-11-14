package lab10.task2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task2Runner {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        runScenario();
    }

    public static void runScenario() {
        log("=== ПОЧАТОК ДЕМОНСТРАЦІЇ: ЗАВДАННЯ 2 (FACTORY METHOD) ===");

        TicketSalesChannel boxOffice = new BoxOfficeChannel();
        TicketSalesChannel website = new WebsiteChannel();
        TicketSalesChannel mobileApp = new MobileAppChannel();

        log("--- Продаж квитків через базові канали ---");
        boxOffice.sellTicket("Дюна: Частина друга", "Ряд 5, Місце 7", 180.0);
        System.out.println();
        website.sellTicket("Дюна: Частина друга", "Ряд 5, Місце 8", 180.0);
        System.out.println();
        mobileApp.sellTicket("Дюна: Частина друга", "Ряд 6, Місце 1", 180.0);
        System.out.println();

        log("--- Підключення нового каналу без зміни процесу продажу ---");
        TicketSalesChannel kiosk = new SelfServiceKioskChannel();
        kiosk.sellTicket("Дюна: Частина друга", "Ряд 6, Місце 2", 180.0);

        log("=== ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО ===");
    }

    private static void log(String message) {
        System.out.printf("[%s] [Раннер Завдання 2] %s%n", LocalTime.now().format(TIME_FORMAT), message);
    }
}
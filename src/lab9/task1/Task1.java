package lab9.task1;

import java.time.LocalDateTime;

/** Демонстрація Singleton для варіанта 4 — кінотеатр «Кадр». */
public final class Task1 {
    private Task1() {
    }

    public static void main(String[] args) {
        CinemaTicketingSystem cashierSystem = CinemaTicketingSystem.getInstance();
        CinemaTicketingSystem websiteSystem = CinemaTicketingSystem.getInstance();
        CinemaTicketingSystem mobileAppSystem = CinemaTicketingSystem.getInstance();

        printSystemId(SalesChannel.CASHIER, cashierSystem);
        printSystemId(SalesChannel.WEBSITE, websiteSystem);
        printSystemId(SalesChannel.MOBILE_APP, mobileAppSystem);

        Screening screening = new Screening(
                "Вечірній сеанс",
                2,
                LocalDateTime.of(2026, 11, 5, 19, 0)
        );
        Seat seat = new Seat(5, 7);

        cashierSystem.sellSeat(screening, seat, SalesChannel.CASHIER);
        System.out.println("Каса продала місце " + seat + " на сеанс 19:00.");

        try {
            mobileAppSystem.sellSeat(screening, seat, SalesChannel.MOBILE_APP);
        } catch (SeatAlreadySoldException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void printSystemId(SalesChannel channel, CinemaTicketingSystem system) {
        System.out.println("ID системи (" + channel.getDisplayName() + "): " + system.getSystemId());
    }
}

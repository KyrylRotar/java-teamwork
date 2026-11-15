package lab10.task2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class TicketSalesChannel {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String channelName;

    public TicketSalesChannel(String channelName) {
        this.channelName = channelName;
    }

    // Загальний незмінний процес продажу (без if/switch за типом каналу)
    public final Ticket sellTicket(String movie, String seat, double price) {
        log("1. Резервування місця " + seat + " на фільм '" + movie + "'...");
        log("2. Прийом оплати у розмірі " + price + " грн...");

        // Виклик фабричного методу
        Ticket ticket = createTicket(movie, seat);

        log("3. Видача квитка: " + ticket.getFormattedOutput());
        log("4. Запис продажу в базу кінотеатру «Кадр».");
        return ticket;
    }

    // Фабричний метод, який перевизначають канали
    protected abstract Ticket createTicket(String movie, String seat);

    protected void log(String message) {
        System.out.printf("[%s] [%s] %s%n", LocalTime.now().format(TIME_FORMAT), channelName, message);
    }
}
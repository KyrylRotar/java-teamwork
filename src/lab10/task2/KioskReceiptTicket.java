package lab10.task2;

public class KioskReceiptTicket implements Ticket {
    private final String movie;
    private final String seat;

    public KioskReceiptTicket(String movie, String seat) {
        this.movie = movie;
        this.seat = seat;
    }

    @Override
    public String getType() { return "Квиток-чек (Термінал самообслуговування)"; }

    @Override
    public String getDetails() { return "Компактний QR-талон зі швидкого принтера"; }

    @Override
    public String getFormattedOutput() {
        return String.format("[%s] Фільм: '%s' | Місце: %s | %s", getType(), movie, seat, getDetails());
    }
}
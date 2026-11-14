package lab10.task2;

public class WalletTicket implements Ticket {
    private final String movie;
    private final String seat;

    public WalletTicket(String movie, String seat) {
        this.movie = movie;
        this.seat = seat;
    }

    @Override
    public String getType() { return "Електронний квиток (Застосунок)"; }

    @Override
    public String getDetails() { return "Apple/Google Wallet pass з NFC-міткою"; }

    @Override
    public String getFormattedOutput() {
        return String.format("[%s] Фільм: '%s' | Місце: %s | %s", getType(), movie, seat, getDetails());
    }
}
package lab10.task2;

public class PaperTicket implements Ticket {
    private final String movie;
    private final String seat;

    public PaperTicket(String movie, String seat) {
        this.movie = movie;
        this.seat = seat;
    }

    @Override
    public String getType() { return "Паперовий квиток (Каса)"; }

    @Override
    public String getDetails() { return "Термодрук, фіскальний чек, штрих-код"; }

    @Override
    public String getFormattedOutput() {
        return String.format("[%s] Фільм: '%s' | Місце: %s | %s", getType(), movie, seat, getDetails());
    }
}

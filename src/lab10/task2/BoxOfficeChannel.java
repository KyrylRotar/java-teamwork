package lab10.task2;

public class BoxOfficeChannel extends TicketSalesChannel {
    public BoxOfficeChannel() { super("Каса у фоє"); }

    @Override
    protected Ticket createTicket(String movie, String seat) {
        return new PaperTicket(movie, seat);
    }
}
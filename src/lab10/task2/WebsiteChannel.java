package lab10.task2;

public class WebsiteChannel extends TicketSalesChannel {
    public WebsiteChannel() { super("Вебсайт кінотеатру"); }

    @Override
    protected Ticket createTicket(String movie, String seat) {
        return new PdfTicket(movie, seat);
    }
}
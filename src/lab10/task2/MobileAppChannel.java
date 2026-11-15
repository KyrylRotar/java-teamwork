package lab10.task2;

public class MobileAppChannel extends TicketSalesChannel {
    public MobileAppChannel() { super("Мобільний застосунок"); }

    @Override
    protected Ticket createTicket(String movie, String seat) {
        return new WalletTicket(movie, seat);
    }
}
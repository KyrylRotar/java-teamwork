package lab10.task2;

public class SelfServiceKioskChannel extends TicketSalesChannel {
    public SelfServiceKioskChannel() { super("Термінал самообслуговування"); }

    @Override
    protected Ticket createTicket(String movie, String seat) {
        return new KioskReceiptTicket(movie, seat);
    }
}
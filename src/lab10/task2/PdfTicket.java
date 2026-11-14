package lab10.task2;

public class PdfTicket implements Ticket {
    private final String movie;
    private final String seat;

    public PdfTicket(String movie, String seat) {
        this.movie = movie;
        this.seat = seat;
    }

    @Override
    public String getType() { return "PDF-квиток (Сайт)"; }

    @Override
    public String getDetails() { return "Файл A4 з QR-кодом, надіслано на e-mail"; }

    @Override
    public String getFormattedOutput() {
        return String.format("[%s] Фільм: '%s' | Місце: %s | %s", getType(), movie, seat, getDetails());
    }
}
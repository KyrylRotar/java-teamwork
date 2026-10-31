package lab7.task1;

/** Результат успішного оформлення: бронювання та HTML-ваучер для гостя. */
public final class BookingConfirmation {
    private final Booking booking;
    private final String voucherHtml;

    public BookingConfirmation(Booking booking, String voucherHtml) {
        this.booking = booking;
        this.voucherHtml = voucherHtml;
    }

    public Booking getBooking() {
        return booking;
    }

    public String getVoucherHtml() {
        return voucherHtml;
    }
}

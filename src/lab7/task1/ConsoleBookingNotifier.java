package lab7.task1;

import java.util.Locale;

/**
 * Демонстраційний канал: виводить повідомлення в консоль.
 * Для реальної інтеграції його можна замінити адаптером SMS/Viber API.
 */
public final class ConsoleBookingNotifier implements BookingNotifier {
    @Override
    public void sendConfirmation(Booking booking) {
        BookingRequest request = booking.getRequest();
        String message = String.format(
                Locale.forLanguageTag("uk-UA"),
                "Шановний(а) %s, бронювання №%s на %s підтверджено. Сума: %.2f грн.",
                request.getGuestName(),
                booking.getId(),
                UkrainianText.nights(booking.getPrice().getNights()),
                booking.getPrice().getGrandTotal()
        );

        System.out.printf("Демонстраційне надсилання на номер %s:%n%s%n", request.getGuestPhone(), message);
    }
}

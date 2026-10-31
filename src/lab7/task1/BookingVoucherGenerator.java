package lab7.task1;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

/** Формує HTML-ваучер і безпечно екранує дані гостя перед вставленням у розмітку. */
public final class BookingVoucherGenerator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String generate(Booking booking) {
        BookingRequest request = booking.getRequest();
        BookingPriceBreakdown price = booking.getPrice();
        String services = request.getExtraServices().isEmpty()
                ? "Немає"
                : request.getExtraServices().stream()
                        .map(ExtraService::getDisplayName)
                        .collect(Collectors.joining(", "));

        return """
                <!doctype html>
                <html lang="uk">
                <head><meta charset="UTF-8"><title>Ваучер бронювання</title></head>
                <body>
                  <h2>Ваучер бронювання №%s</h2>
                  <p><b>Гість:</b> %s</p>
                  <p><b>Тип номера:</b> %s</p>
                  <p><b>Заїзд:</b> %s | <b>Виїзд:</b> %s (%s)</p>
                  <p><b>Додаткові послуги:</b> %s</p>
                  <p><b>Вартість номера:</b> %s грн</p>
                  <p><b>Додаткові послуги:</b> %s грн</p>
                  <p><b>Знижка учасника програми лояльності:</b> %s грн</p>
                  <h3>Загальна вартість: %s грн</h3>
                </body>
                </html>
                """.formatted(
                escapeHtml(booking.getId()),
                escapeHtml(request.getGuestName()),
                escapeHtml(request.getRoomType().getDisplayName()),
                request.getCheckInDate().format(DATE_FORMAT),
                request.getCheckOutDate().format(DATE_FORMAT),
                UkrainianText.nights(price.getNights()),
                escapeHtml(services),
                money(price.getRoomTotal()),
                money(price.getExtrasTotal()),
                money(price.getLoyaltyDiscount()),
                money(price.getGrandTotal())
        );
    }

    private static String money(java.math.BigDecimal amount) {
        return String.format(Locale.ROOT, "%.2f", amount);
    }

    private static String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}

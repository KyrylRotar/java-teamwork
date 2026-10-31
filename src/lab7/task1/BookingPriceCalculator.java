package lab7.task1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

/** Відповідає лише за правила ціноутворення готелю. */
public final class BookingPriceCalculator {
    private static final BigDecimal SUMMER_MULTIPLIER = new BigDecimal("1.30");
    private static final BigDecimal LOYALTY_DISCOUNT = new BigDecimal("0.10");

    public BookingPriceBreakdown calculate(BookingRequest request) {
        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());

        // Зберігаємо правило з варіанта: сезон визначається місяцем дати заїзду.
        int checkInMonth = request.getCheckInDate().getMonthValue();
        BigDecimal nightlyRate = request.getRoomType().getPricePerNight();
        if (checkInMonth >= 6 && checkInMonth <= 8) {
            nightlyRate = nightlyRate.multiply(SUMMER_MULTIPLIER);
        }

        BigDecimal roomTotal = nightlyRate.multiply(BigDecimal.valueOf(nights));
        BigDecimal extrasTotal = request.getExtraServices().stream()
                .map(service -> service.isChargedPerNight()
                        ? service.getPrice().multiply(BigDecimal.valueOf(nights))
                        : service.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal subtotal = roomTotal.add(extrasTotal);
        BigDecimal discount = request.isLoyaltyMember()
                ? subtotal.multiply(LOYALTY_DISCOUNT).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        return new BookingPriceBreakdown(
                nights,
                roomTotal.setScale(2, RoundingMode.HALF_UP),
                extrasTotal.setScale(2, RoundingMode.HALF_UP),
                discount,
                subtotal.subtract(discount).setScale(2, RoundingMode.HALF_UP)
        );
    }
}

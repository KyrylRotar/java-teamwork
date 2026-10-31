package lab7.task1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/** Координує кроки сценарію бронювання, не реалізуючи їхню внутрішню логіку. */
public final class HotelBookingService {
    private final BookingValidator validator;
    private final BookingPriceCalculator priceCalculator;
    private final BookingVoucherGenerator voucherGenerator;
    private final BookingRepository repository;
    private final BookingNotifier notifier;

    public HotelBookingService(
            BookingValidator validator,
            BookingPriceCalculator priceCalculator,
            BookingVoucherGenerator voucherGenerator,
            BookingRepository repository,
            BookingNotifier notifier
    ) {
        this.validator = Objects.requireNonNull(validator);
        this.priceCalculator = Objects.requireNonNull(priceCalculator);
        this.voucherGenerator = Objects.requireNonNull(voucherGenerator);
        this.repository = Objects.requireNonNull(repository);
        this.notifier = Objects.requireNonNull(notifier);
    }

    /**
     * Перевіряє заявку, створює розрахунок і ваучер, зберігає бронювання та надсилає підтвердження.
     * Помилки збереження або надсилання не приховуються від викликувача.
     */
    public BookingConfirmation createBooking(BookingRequest request) throws IOException {
        validator.validate(request);

        String bookingId = UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase(Locale.ROOT);
        Booking booking = new Booking(
                bookingId,
                request,
                priceCalculator.calculate(request),
                LocalDateTime.now()
        );
        String voucherHtml = voucherGenerator.generate(booking);

        repository.save(booking);
        notifier.sendConfirmation(booking);

        return new BookingConfirmation(booking, voucherHtml);
    }
}

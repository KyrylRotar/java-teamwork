package lab7.task1;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/** Точка запуску прикладу для варіанта 4 — система готельних бронювань. */
public final class Task1 {
    private Task1() {
    }

    public static void main(String[] args) throws IOException {
        HotelBookingService bookingService = new HotelBookingService(
                new BookingValidator(),
                new BookingPriceCalculator(),
                new BookingVoucherGenerator(),
                new FileBookingRepository(Path.of("hotel_bookings.log")),
                new ConsoleBookingNotifier()
        );

        LocalDate checkIn = LocalDate.now().plusDays(30);
        BookingRequest request = new BookingRequest(
                "Олена Коваль",
                "+380501234567",
                RoomType.DELUXE,
                checkIn,
                checkIn.plusDays(3),
                true,
                List.of(ExtraService.BREAKFAST, ExtraService.SPA)
        );

        BookingConfirmation confirmation = bookingService.createBooking(request);
        System.out.println("Ваучер створено для бронювання №" + confirmation.getBooking().getId() + ".");
        System.out.println(confirmation.getVoucherHtml());
    }
}

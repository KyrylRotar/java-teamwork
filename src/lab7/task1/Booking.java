package lab7.task1;

import java.time.LocalDateTime;
import java.util.Objects;

/** Створене бронювання разом із зафіксованим на момент створення розрахунком. */
public final class Booking {
    private final String id;
    private final BookingRequest request;
    private final BookingPriceBreakdown price;
    private final LocalDateTime createdAt;

    public Booking(String id, BookingRequest request, BookingPriceBreakdown price, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id);
        this.request = Objects.requireNonNull(request);
        this.price = Objects.requireNonNull(price);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public String getId() {
        return id;
    }

    public BookingRequest getRequest() {
        return request;
    }

    public BookingPriceBreakdown getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

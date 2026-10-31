package lab7.task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Незмінні дані, які гість передає для створення бронювання.
 * Перевірку коректності значень виконує окремий BookingValidator.
 */
public final class BookingRequest {
    private final String guestName;
    private final String guestPhone;
    private final RoomType roomType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final boolean loyaltyMember;
    private final List<ExtraService> extraServices;

    public BookingRequest(
            String guestName,
            String guestPhone,
            RoomType roomType,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            boolean loyaltyMember,
            List<ExtraService> extraServices
    ) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.loyaltyMember = loyaltyMember;
        this.extraServices = extraServices == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(new ArrayList<>(extraServices));
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public List<ExtraService> getExtraServices() {
        return extraServices;
    }
}

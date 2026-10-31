package lab7.task1;

import java.math.BigDecimal;

/** Категорії номерів та їх базова вартість за одну ніч. */
public enum RoomType {
    STANDARD("Standard", new BigDecimal("1200")),
    DELUXE("Deluxe", new BigDecimal("2500")),
    SUITE("Suite", new BigDecimal("4500"));

    private final String displayName;
    private final BigDecimal pricePerNight;

    RoomType(String displayName, BigDecimal pricePerNight) {
        this.displayName = displayName;
        this.pricePerNight = pricePerNight;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }
}

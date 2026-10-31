package lab7.task1;

import java.math.BigDecimal;

/** Вартість додаткової послуги: за ніч або одноразово за бронювання. */
public enum ExtraService {
    BREAKFAST("Breakfast", new BigDecimal("300"), true),
    SPA("Spa", new BigDecimal("800"), false),
    TRANSFER("Transfer", new BigDecimal("500"), false);

    private final String displayName;
    private final BigDecimal price;
    private final boolean chargedPerNight;

    ExtraService(String displayName, BigDecimal price, boolean chargedPerNight) {
        this.displayName = displayName;
        this.price = price;
        this.chargedPerNight = chargedPerNight;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isChargedPerNight() {
        return chargedPerNight;
    }
}

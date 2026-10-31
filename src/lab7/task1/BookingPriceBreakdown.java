package lab7.task1;

import java.math.BigDecimal;

/** Розрахунок вартості бронювання, підготовлений калькулятором. */
public final class BookingPriceBreakdown {
    private final long nights;
    private final BigDecimal roomTotal;
    private final BigDecimal extrasTotal;
    private final BigDecimal loyaltyDiscount;
    private final BigDecimal grandTotal;

    public BookingPriceBreakdown(
            long nights,
            BigDecimal roomTotal,
            BigDecimal extrasTotal,
            BigDecimal loyaltyDiscount,
            BigDecimal grandTotal
    ) {
        this.nights = nights;
        this.roomTotal = roomTotal;
        this.extrasTotal = extrasTotal;
        this.loyaltyDiscount = loyaltyDiscount;
        this.grandTotal = grandTotal;
    }

    public long getNights() {
        return nights;
    }

    public BigDecimal getRoomTotal() {
        return roomTotal;
    }

    public BigDecimal getExtrasTotal() {
        return extrasTotal;
    }

    public BigDecimal getLoyaltyDiscount() {
        return loyaltyDiscount;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }
}

package lab8.task4;

import java.math.BigDecimal;

public final class RareLoanTermsPolicy implements LoanTermsPolicy {
    @Override
    public BookCategory category() {
        return BookCategory.RARE;
    }

    @Override
    public BigDecimal depositRate() {
        return new BigDecimal("0.50");
    }

    @Override
    public BigDecimal serviceFee() {
        return new BigDecimal("250.00");
    }
}

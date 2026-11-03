package lab8.task4;

import java.math.BigDecimal;

public final class StandardLoanTermsPolicy implements LoanTermsPolicy {
    @Override
    public BookCategory category() {
        return BookCategory.STANDARD;
    }

    @Override
    public BigDecimal depositRate() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal serviceFee() {
        return new BigDecimal("50.00");
    }
}

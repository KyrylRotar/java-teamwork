package lab8.task4;

import java.math.BigDecimal;

public final class AcademicLoanTermsPolicy implements LoanTermsPolicy {
    @Override
    public BookCategory category() {
        return BookCategory.ACADEMIC;
    }

    @Override
    public BigDecimal depositRate() {
        return new BigDecimal("0.20");
    }

    @Override
    public BigDecimal serviceFee() {
        return new BigDecimal("100.00");
    }
}

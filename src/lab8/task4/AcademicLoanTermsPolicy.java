package lab8.task4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class AcademicLoanTermsPolicy implements LoanTermsPolicy {
    @Override
    public LoanTerms calculate(Book book) {
        requireCategory(book, BookCategory.ACADEMIC);
        BigDecimal deposit = book.valueAmount().multiply(new BigDecimal("0.20"))
                .setScale(2, RoundingMode.HALF_UP);
        return new LoanTerms(deposit, new BigDecimal("100.00"));
    }

    private static void requireCategory(Book book, BookCategory expected) {
        if (book.category() != expected) {
            throw new IllegalArgumentException("Для цієї політики потрібна категорія " + expected.getLabel() + ".");
        }
    }
}

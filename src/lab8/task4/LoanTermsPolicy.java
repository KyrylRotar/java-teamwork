package lab8.task4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface LoanTermsPolicy {
    BookCategory category();

    BigDecimal depositRate();

    BigDecimal serviceFee();

    default LoanTerms calculate(Book book) {
        if (book.category() != category()) {
            throw new IllegalArgumentException(
                    "Політика " + category().getLabel() + " не може обробити книгу категорії "
                            + book.category().getLabel() + "."
            );
        }
        BigDecimal deposit = book.valueAmount()
                .multiply(depositRate())
                .setScale(2, RoundingMode.HALF_UP);
        return new LoanTerms(deposit, serviceFee());
    }
}

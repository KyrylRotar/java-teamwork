package lab8.task4;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Task4 {
    private Task4() {
    }

    public static void main(String[] args) {
        LoanPolicyRegistry policies = LoanPolicyRegistry.defaults();
        Book book = new Book(
                104,
                "Алгоритми та структури даних",
                BookCategory.from(args.length == 0 ? "Standard" : args[0]),
                new BigDecimal("1500.00")
        );
        LoanRecord loan = LoanRecord.issue(
                8801,
                "Олена Коваль",
                "T-8841",
                book,
                LocalDate.now(),
                14,
                policies
        );

        System.out.println(loan.receipt());
    }
}

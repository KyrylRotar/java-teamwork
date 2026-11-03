package lab8.task4;

import java.time.LocalDate;
import java.util.Objects;

public record LoanRecord(
        int loanId,
        String readerName,
        String ticketNumber,
        Book book,
        LocalDate issuedOn,
        LocalDate dueOn,
        LoanTerms terms
) {
    public LoanRecord {
        if (loanId <= 0) {
            throw new IllegalArgumentException("Ідентифікатор видачі має бути додатним.");
        }
        if (readerName == null || readerName.isBlank()) {
            throw new IllegalArgumentException("Ім'я читача не може бути порожнім.");
        }
        if (ticketNumber == null || ticketNumber.isBlank()) {
            throw new IllegalArgumentException("Номер читацького квитка не може бути порожнім.");
        }
        Objects.requireNonNull(book, "Книга обов'язкова.");
        Objects.requireNonNull(issuedOn, "Дата видачі обов'язкова.");
        Objects.requireNonNull(dueOn, "Дата повернення обов'язкова.");
        Objects.requireNonNull(terms, "Умови видачі обов'язкові.");
        if (dueOn.isBefore(issuedOn)) {
            throw new IllegalArgumentException("Дата повернення не може передувати даті видачі.");
        }
    }

    public static LoanRecord issue(
            int loanId,
            String readerName,
            String ticketNumber,
            Book book,
            LocalDate issuedOn,
            int loanDays,
            LoanPolicyRegistry policies
    ) {
        if (loanDays <= 0) {
            throw new IllegalArgumentException("Тривалість видачі має бути додатною.");
        }
        Objects.requireNonNull(policies, "Реєстр політик обов'язковий.");
        return new LoanRecord(
                loanId,
                readerName,
                ticketNumber,
                book,
                issuedOn,
                issuedOn.plusDays(loanDays),
                policies.calculate(book)
        );
    }

    public String receipt() {
        return "Квитанція видачі #" + loanId
                + "\nЧитач: " + readerName + " (" + ticketNumber + ")"
                + "\nКнига: " + book.title()
                + "\nКатегорія: " + book.category().getLabel()
                + "\nПовернути до: " + dueOn
                + "\nЗастава: " + terms.deposit() + " грн"
                + "\nСервісний збір: " + terms.serviceFee() + " грн"
                + "\nРазом: " + terms.totalPayable() + " грн";
    }
}

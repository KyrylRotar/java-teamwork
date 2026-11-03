package lab8.task3;

import lab8.task2.*;
import lab8.task4.LoanTermsPolicy;
import lab8.task4.LoanTerms;
import java.math.BigDecimal;

public class LoanController {
    private final IAuditLogger auditLogger;
    private final IEmailNotifier emailNotifier;
    private final LoanTermsPolicy termsPolicy;
    private int loanSequence = 8801;

    public LoanController(IAuditLogger auditLogger, IEmailNotifier emailNotifier, LoanTermsPolicy termsPolicy) {
        this.auditLogger = auditLogger;
        this.emailNotifier = emailNotifier;
        this.termsPolicy = termsPolicy;
    }

    public LoanReceiptDTO processLoan(LoanRequestDTO request) {
        // 1. Отримання доменних сутностей Task2
        lab8.task2.BookCategory categoryTask2 = lab8.task2.BookCategory.valueOf(request.bookCategoryStr().toUpperCase());
        lab8.task2.Book book = new lab8.task2.Book(request.bookId(), "Алгоритми та структури даних", categoryTask2, 1500.0);
        Reader reader = new Reader(request.readerId(), request.readerName(), "T-8841", 2, "reader@univ.edu");

        // 2. Перевірка інваріантів (Fail-fast)
        if (!reader.canBorrowMoreBooks(5)) {
            throw new IllegalStateException("Перевищено ліміт активних видач для читача.");
        }

        // 3. Створення книги сумісного типу з BigDecimal
        lab8.task4.BookCategory categoryTask4 = lab8.task4.BookCategory.valueOf(request.bookCategoryStr().toUpperCase());
        lab8.task4.Book task4Book = new lab8.task4.Book(
            request.bookId(),
            "Алгоритми та структури даних",
            categoryTask4,
            BigDecimal.valueOf(1500.0)
        );

        // Розрахунок умов через поліморфну політику 
        LoanTerms terms = termsPolicy.calculate(task4Book);

        double deposit = terms.deposit().doubleValue();
        double fee = terms.serviceFee().doubleValue();

        // 4. Створення агрегату LoanRecord (Creator / Information Expert)
        LoanRecord loan = new LoanRecord(loanSequence++, reader, book, request.days(), deposit, fee);

        // 5. Логування та сповіщення (Pure Fabrication)
        auditLogger.logLoan(loan.getBookId(), loan.getReaderId());
        emailNotifier.sendNotification(loan.getReaderEmail(), "Оформлено видачу книги: " + loan.getBookTitle());

        // 6. Повернення DTO без витоку деталей домену
        return new LoanReceiptDTO(
            loan.getLoanId(),
            loan.getReaderName(),
            loan.getReaderTicketNumber(),
            loan.getBookTitle(),
            loan.getDepositFee(),
            loan.getServiceFee(),
            loan.getTotalPayable()
        );
    }
}
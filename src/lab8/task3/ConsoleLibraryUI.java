package lab8.task3;

import lab8.task4.BookCategory;
import lab8.task4.LoanPolicyRegistry;
import lab8.task4.LoanTermsPolicy;
import lab8.task4.StandardLoanTermsPolicy;
import lab8.task4.AcademicLoanTermsPolicy;
import lab8.task4.RareLoanTermsPolicy;

import java.util.Map;
import java.util.Scanner;

public class ConsoleLibraryUI {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Реєстрація видачі книги (GRASP Architecture) ===");
        System.out.print("Введіть ім'я читача: ");
        String name = scanner.nextLine();
        System.out.print("Введіть категорію книги (Standard/Rare/Academic): ");
        String category = scanner.nextLine().trim();

        // Формування DTO запиту
        LoanRequestDTO request = new LoanRequestDTO(7, name, 104, category, 14);

        // Збірка інфраструктурних компонентів (Pure Fabrication)
        IAuditLogger logger = new FileAuditLogger();
        IEmailNotifier notifier = new EmailNotificationService();

        // Налаштування реєстру політик Кирила (Task 4)
        Map<BookCategory, LoanTermsPolicy> policies = Map.of(
            BookCategory.STANDARD, new StandardLoanTermsPolicy(),
            BookCategory.ACADEMIC, new AcademicLoanTermsPolicy(),
            BookCategory.RARE, new RareLoanTermsPolicy()
        );
        LoanPolicyRegistry registry = new LoanPolicyRegistry(policies);

        // Створення контролера та виконання Use-Case
        LoanController controller = new LoanController(logger, notifier, registry::calculate);
        LoanReceiptDTO receipt = controller.processLoan(request);

        // Форматування квитанції на рівні UI
        System.out.println("\nКвитанція видачі #" + receipt.loanId());
        System.out.println("Читач: " + receipt.readerName() + " (" + receipt.ticketNumber() + ")");
        System.out.println("Книга: " + receipt.bookTitle());
        System.out.println("Застава: " + receipt.depositFee() + " грн");
        System.out.println("Сервісний збір: " + receipt.serviceFee() + " грн");
        System.out.println("Разом: " + receipt.totalPayable() + " грн");
    }
}
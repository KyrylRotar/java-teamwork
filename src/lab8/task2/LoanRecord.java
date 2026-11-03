package lab8.task2;

import java.util.Objects;

/**
 * Rich Domain Model.
 * Реалізує патерни Information Expert та Creator.
 */
public class LoanRecord {
    private final int loanId;
    private final Reader reader;
    private final Book book;
    private final int daysCount;
    private final double depositFee;
    private final double serviceFee;
    private final double totalPayable;
    private String status;

    // Creator: конструювання об'єкта одразу у валідному стані
    public LoanRecord(int loanId, Reader reader, Book book, int daysCount, double depositFee, double serviceFee) {
        this.loanId = loanId;
        this.reader = Objects.requireNonNull(reader, "Читач не може бути null");
        this.book = Objects.requireNonNull(book, "Книга не може бути null");
        this.daysCount = daysCount;
        this.depositFee = depositFee;
        this.serviceFee = serviceFee;
        // Information Expert: сам розраховує підсумкову суму до сплати
        this.totalPayable = calculateTotal(depositFee, serviceFee);
        this.status = "Issued";
        this.reader.incrementLoans();
    }

    // Information Expert: інкапсульована формула підрахунку вартості
    private double calculateTotal(double deposit, double fee) {
        return deposit + fee;
    }

    // Дотримання Закону Деметри: делегування запиту інформаційному експерту
    public String getReaderName() { return reader.getName(); }
    public String getReaderTicketNumber() { return reader.getTicketNumber(); }
    public String getReaderEmail() { return reader.getEmail(); }
    public String getBookTitle() { return book.getTitle(); }
    public int getBookId() { return book.getId(); }
    public int getReaderId() { return reader.getId(); }

    public int getLoanId() { return loanId; }
    public Reader getReader() { return reader; }
    public Book getBook() { return book; }
    public int getDaysCount() { return daysCount; }
    public double getDepositFee() { return depositFee; }
    public double getServiceFee() { return serviceFee; }
    public double getTotalPayable() { return totalPayable; }
    public String getStatus() { return status; }
}
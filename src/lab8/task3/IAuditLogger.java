package lab8.task3;

public interface IAuditLogger {
    void logLoan(int bookId, int readerId);
}
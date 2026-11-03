package lab8.task3;

public class FileAuditLogger implements IAuditLogger {
    @Override
    public void logLoan(int bookId, int readerId) {
        System.out.println("[Журнал логів] Видачу книги #" + bookId + " читачу #" + readerId + " записано в library_audit.log.");
    }
}
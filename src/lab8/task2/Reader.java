package lab8.task2;

public class Reader {
    private final int id;
    private final String name;
    private final String ticketNumber;
    private int activeLoansCount;
    private final String email;

    public Reader(int id, String name, String ticketNumber, int activeLoansCount, String email) {
        if (id <= 0) throw new IllegalArgumentException("Некоректний ID читача.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Ім'я читача обов'язкове.");
        this.id = id;
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.activeLoansCount = Math.max(0, activeLoansCount);
        this.email = email;
    }

    // Information Expert: Reader сам знає свій ліміт і перевіряє можливість видачі
    public boolean canBorrowMoreBooks(int maxAllowedLimit) {
        return this.activeLoansCount < maxAllowedLimit;
    }

    public void incrementLoans() {
        this.activeLoansCount++;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getTicketNumber() { return ticketNumber; }
    public int getActiveLoansCount() { return activeLoansCount; }
    public String getEmail() { return email; }
}
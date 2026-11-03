package lab8.task3;

public record LoanReceiptDTO(
    int loanId,
    String readerName,
    String ticketNumber,
    String bookTitle,
    double depositFee,
    double serviceFee,
    double totalPayable
) {}
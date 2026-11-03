package lab8.task3;

public record LoanRequestDTO(
    int readerId,
    String readerName,
    int bookId,
    String bookCategoryStr,
    int days
) {}
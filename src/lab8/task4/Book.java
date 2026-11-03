package lab8.task4;

import java.math.BigDecimal;
import java.util.Objects;

public record Book(int id, String title, BookCategory category, BigDecimal valueAmount) {
    public Book {
        if (id <= 0) {
            throw new IllegalArgumentException("Ідентифікатор книги має бути додатним.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Назва книги не може бути порожньою.");
        }
        Objects.requireNonNull(category, "Категорія книги обов'язкова.");
        Objects.requireNonNull(valueAmount, "Вартість книги обов'язкова.");
        if (valueAmount.signum() < 0) {
            throw new IllegalArgumentException("Вартість книги не може бути від'ємною.");
        }
    }
}

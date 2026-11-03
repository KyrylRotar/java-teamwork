package lab8.task4;

import java.util.Locale;

public enum BookCategory {
    STANDARD("Standard"),
    RARE("Rare"),
    ACADEMIC("Academic");

    private final String label;

    BookCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static BookCategory from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Категорія книги не може бути порожньою.");
        }
        try {
            return valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Невідома категорія книги: " + value, exception);
        }
    }
}

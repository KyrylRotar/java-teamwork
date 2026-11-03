package lab8.task2;

public class Book {
    private final int id;
    private final String title;
    private final BookCategory category;
    private final double valueAmount;

    public Book(int id, String title, BookCategory category, double valueAmount) {
        if (id <= 0) throw new IllegalArgumentException("ID книги має бути додатним.");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Назва книги не може бути порожньою.");
        if (valueAmount < 0) throw new IllegalArgumentException("Оціночна вартість не може бути від'ємною.");
        this.id = id;
        this.title = title;
        this.category = category;
        this.valueAmount = valueAmount;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public BookCategory getCategory() { return category; }
    public double getValueAmount() { return valueAmount; }
}
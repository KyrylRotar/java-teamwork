package lab12.task2;

import java.util.Objects;

public final class CinemaSeat {
    private final String sessionId;
    private final String row;
    private final int number;
    private final SeatCategory category;
    private boolean sold;

    public CinemaSeat(String sessionId, String row, int number, SeatCategory category) {
        this.sessionId = Objects.requireNonNull(sessionId, "Ідентифікатор сеансу не може бути null.");
        this.row = Objects.requireNonNull(row, "Ряд місця не може бути null.");
        if (sessionId.isBlank() || row.isBlank()) {
            throw new IllegalArgumentException("Ідентифікатор сеансу та ряд не можуть бути порожніми.");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("Номер місця має бути більшим за нуль.");
        }
        this.number = number;
        this.category = Objects.requireNonNull(category, "Категорія місця не може бути null.");
    }

    public void sell() {
        if (sold) {
            throw new IllegalStateException("Місце " + getSeatId() + " вже продано.");
        }
        sold = true;
    }

    public String getSeatId() {
        return "Сеанс " + sessionId + ", ряд " + row + ", місце " + number;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public boolean isSold() {
        return sold;
    }

    public String getFullDescription() {
        return getSeatId()
                + " | категорія: " + category.getName()
                + " | опис: " + category.getDescription()
                + " | ширина крісла: " + category.getWidthCm() + " см"
                + " | значок: " + category.getIconImage()
                + " | базова ціна: " + String.format("%.2f", category.getBasePrice()) + " грн"
                + " | стан: " + (sold ? "продано" : "вільне");
    }
}

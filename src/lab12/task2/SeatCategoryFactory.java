package lab12.task2;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class SeatCategoryFactory {
    private final Map<SeatCategoryType, SeatCategory> categories = new EnumMap<>(SeatCategoryType.class);

    public SeatCategory getCategory(SeatCategoryType type) {
        Objects.requireNonNull(type, "Тип категорії місця не може бути null.");
        return categories.computeIfAbsent(type, SeatCategory::new);
    }

    public int getCreatedCategoryCount() {
        return categories.size();
    }
}

package lab7.task2;

import java.util.Locale;
import java.util.Objects;

/** Ідентифікатор категорії товару.
 * На відміну від enum, нову категорію можна додати окремим модулем без зміни
 * цього класу чи калькулятора податків.
 */
public final class ProductCategory {
    public static final ProductCategory ELECTRONICS = new ProductCategory("ELECTRONICS", "Електроніка");
    public static final ProductCategory FOOD = new ProductCategory("FOOD", "Продукти харчування");
    public static final ProductCategory LUXURY_GOODS = new ProductCategory("LUXURY_GOODS", "Предмети розкоші");
    public static final ProductCategory PHARMACEUTICALS = new ProductCategory("PHARMACEUTICALS", "Медикаменти");

    private final String code;
    private final String displayName;

    /**
     * Створює категорію з унікальним машинним кодом та назвою для відображення.
     * Код є ключем пошуку стратегії та порівнюється без урахування регістру.
     */
    public ProductCategory(String code, String displayName) {
        Objects.requireNonNull(code, "Код категорії не може бути null.");
        String normalizedCode = code.trim().toUpperCase(Locale.ROOT);
        if (!normalizedCode.matches("[A-Z][A-Z0-9_]*")) {
            throw new IllegalArgumentException(
                    "Код категорії має починатися з літери та містити лише літери, цифри або '_'."
            );
        }

        Objects.requireNonNull(displayName, "Назва категорії не може бути null.");
        if (displayName.isBlank()) {
            throw new IllegalArgumentException("Назва категорії не може бути порожньою.");
        }

        this.code = normalizedCode;
        this.displayName = displayName.trim();
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProductCategory that)) {
            return false;
        }
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return displayName;
    }
}

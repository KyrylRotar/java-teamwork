package lab7.task3;

import java.math.BigDecimal;
import java.util.Objects;

/** Незмінні атрибути товару, спільні для фізичних і цифрових позицій каталогу. */
public abstract class AbstractInventoryItem implements InventoryItem {
    private final String sku;
    private final String title;
    private final BigDecimal unitPrice;

    protected AbstractInventoryItem(String sku, String title, BigDecimal unitPrice) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("Артикул товару не може бути порожнім.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою.");
        }
        this.unitPrice = Objects.requireNonNull(unitPrice, "Ціна товару не може бути null.");
        if (unitPrice.signum() < 0) {
            throw new IllegalArgumentException("Ціна товару не може бути від'ємною.");
        }

        this.sku = sku.trim();
        this.title = title.trim();
    }

    @Override
    public final String getSku() {
        return sku;
    }

    @Override
    public final String getTitle() {
        return title;
    }

    @Override
    public final BigDecimal getUnitPrice() {
        return unitPrice;
    }
}

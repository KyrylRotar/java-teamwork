package lab7.task3;

import java.util.List;
import java.util.Objects;

/** Каталог працює з усіма товарами через мінімальний спільний контракт. */
public final class InventoryCatalog {
    public void printItems(List<? extends InventoryItem> items) {
        Objects.requireNonNull(items, "Список товарів не може бути null.");
        for (InventoryItem item : items) {
            Objects.requireNonNull(item, "Список не може містити null.");
            System.out.printf("%s — %s, ціна: %s грн%n",
                    item.getSku(), item.getTitle(), item.getUnitPrice().toPlainString());
        }
    }
}

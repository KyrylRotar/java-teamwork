package lab7.task3;

import java.util.Objects;

/** Складські операції приймають лише товари, що підтримують відповідну дію. */
public final class WarehouseManager {

    public void restock(Restockable item, int quantity) {
        Objects.requireNonNull(item, "Товар для поповнення не може бути null.");
        item.restock(quantity);
        System.out.printf(
                "Поповнено %d од. товару %s. Залишок: %d од.%n",
                quantity,
                item.getTitle(),
                item.getStockQuantity()
        );
    }

    public void processDefectiveReturn(SupplierReturnable item, int quantity) {
        Objects.requireNonNull(item, "Товар для повернення не може бути null.");
        item.returnToSupplier(quantity);
        System.out.printf(
                "Повернено постачальнику %d од. товару %s. Залишок: %d од.%n",
                quantity,
                item.getTitle(),
                item.getStockQuantity()
        );
    }
}

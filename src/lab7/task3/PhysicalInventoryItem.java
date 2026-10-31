package lab7.task3;

import java.math.BigDecimal;

/** Фізичний товар зі скінченним залишком: його можна поповнювати та повертати. */
public final class PhysicalInventoryItem extends AbstractInventoryItem
        implements Restockable, SupplierReturnable {
    private int stockQuantity;

    public PhysicalInventoryItem(String sku, String title, int stockQuantity, BigDecimal unitPrice) {
        super(sku, title, unitPrice);
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Залишок товару не може бути від'ємним.");
        }
        this.stockQuantity = stockQuantity;
    }

    @Override
    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public void restock(int amount) {
        requirePositiveAmount(amount);
        // Перевірка переповнення не дозволяє отримати від'ємний залишок.
        stockQuantity = Math.addExact(stockQuantity, amount);
    }

    @Override
    public void returnToSupplier(int amount) {
        requirePositiveAmount(amount);
        if (amount > stockQuantity) {
            throw new IllegalArgumentException(
                    "Неможливо повернути " + amount + " од.; на складі є лише " + stockQuantity + " од."
            );
        }
        stockQuantity -= amount;
    }

    private static void requirePositiveAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Кількість має бути більшою за нуль.");
        }
    }
}

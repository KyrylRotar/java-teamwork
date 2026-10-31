package lab7.task3;

/** Контракт товарів, які можна повернути постачальнику зі складу. */
public interface SupplierReturnable extends InventoryItem {
    int getStockQuantity();

    void returnToSupplier(int amount);
}

package lab7.task3;

/** Можливість поповнення фізичного складського залишку. */
public interface Restockable extends InventoryItem {
    int getStockQuantity();

    void restock(int amount);
}

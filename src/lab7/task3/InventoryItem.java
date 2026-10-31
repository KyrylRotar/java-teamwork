package lab7.task3;

import java.math.BigDecimal;

/** Спільний контракт для товарів каталогу — лише дані, властиві кожному товару. */
public interface InventoryItem {
    String getSku();

    String getTitle();

    BigDecimal getUnitPrice();
}

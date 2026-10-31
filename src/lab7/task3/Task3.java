package lab7.task3;

import java.math.BigDecimal;
import java.util.List;

/** Демонстрація коректної підстановки фізичних і цифрових товарів (LSP). */
public final class Task3 {
    private Task3() {
    }

    public static void main(String[] args) {
        PhysicalInventoryItem headphones = new PhysicalInventoryItem(
                "PH-100", "Навушники", 20, new BigDecimal("1499.00")
        );
        DigitalLicenseItem officeLicense = new DigitalLicenseItem(
                "DL-200", "Цифрова ліцензія Office", new BigDecimal("2999.00"), "XXXX-XXXX-XXXX"
        );

        // Звичайний каталог приймає обидва види товарів, бо використовує лише спільні властивості.
        new InventoryCatalog().printItems(List.of(headphones, officeLicense));

        // Складські операції доступні лише фізичному товару через відповідні інтерфейси.
        WarehouseManager warehouse = new WarehouseManager();
        warehouse.restock(headphones, 5);
        warehouse.processDefectiveReturn(headphones, 2);

        System.out.println("Шаблон цифрового ключа: " + officeLicense.getLicenseKeyPattern());
        System.out.println("Залишок фізичного товару: " + headphones.getStockQuantity() + " од.");
    }
}

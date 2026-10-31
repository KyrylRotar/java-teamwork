package lab7.task2;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

/** Демонстрація розрахунку податків для варіанта 4. */
public final class Task2 {
    private Task2() {
    }

    public static void main(String[] args) {
        // Composition root: усі доступні політики реєструються без умовної логіки в калькуляторі.
        TaxCalculator calculator = new TaxCalculator(List.of(
                new ElectronicsTaxStrategy(),
                new FoodTaxStrategy(),
                new LuxuryGoodsTaxStrategy(),
                new PharmaceuticalsTaxStrategy()
        ));

        // Однакова митна вартість дає змогу порівняти правила різних категорій.
        ImportGoods goods = new ImportGoods(
                "DECL-001",
                "Імпортний товар",
                new BigDecimal("1000.00"),
                new BigDecimal("10.00")
        );

        for (ProductCategory category : List.of(
                ProductCategory.ELECTRONICS,
                ProductCategory.FOOD,
                ProductCategory.LUXURY_GOODS,
                ProductCategory.PHARMACEUTICALS
        )) {
            printResult(goods, calculator.calculate(goods, category));
        }
    }

    private static void printResult(ImportGoods goods, TaxBreakdown result) {
        System.out.printf(
                Locale.ROOT,
                "[TAX] Товар '%s' (%s): мито=$%s, акциз=$%s, ПДВ (%.0f%%)=$%s | "
                        + "Всього до сплати: $%s%n",
                goods.getName(),
                result.getCategory().getDisplayName(),
                result.getImportDuty().toPlainString(),
                result.getExciseTax().toPlainString(),
                result.getVatRate().multiply(new BigDecimal("100")),
                result.getVatAmount().toPlainString(),
                result.getTotalTax().toPlainString()
        );
    }
}

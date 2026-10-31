package lab7.task2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Детальний результат розрахунку податків для однієї категорії імпортного товару. */
public final class TaxBreakdown {
    private static final int CURRENCY_SCALE = 2;

    private final ProductCategory category;
    private final BigDecimal importDuty;
    private final BigDecimal exciseTax;
    private final BigDecimal vatRate;
    private final BigDecimal vatAmount;
    private final BigDecimal totalTax;

    public TaxBreakdown(
            ProductCategory category,
            BigDecimal importDuty,
            BigDecimal exciseTax,
            BigDecimal vatRate,
            BigDecimal vatAmount
    ) {
        this.category = Objects.requireNonNull(category);
        this.importDuty = currency(importDuty);
        this.exciseTax = currency(exciseTax);
        this.vatRate = Objects.requireNonNull(vatRate);
        this.vatAmount = currency(vatAmount);

        // Підсумок є сумою округлених рядків податків, тому звіт узгоджується арифметично.
        this.totalTax = this.importDuty.add(this.exciseTax).add(this.vatAmount)
                .setScale(CURRENCY_SCALE, RoundingMode.UNNECESSARY);
    }

    private static BigDecimal currency(BigDecimal amount) {
        return Objects.requireNonNull(amount).setScale(CURRENCY_SCALE, RoundingMode.HALF_UP);
    }

    public ProductCategory getCategory() {
        return category;
    }

    public BigDecimal getImportDuty() {
        return importDuty;
    }

    public BigDecimal getExciseTax() {
        return exciseTax;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }
}

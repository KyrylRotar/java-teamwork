package lab7.task2;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Спільний алгоритм для політик, де мито й акциз є відсотками від вартості,
 * а ПДВ нараховується на вартість разом із митом та акцизом.
 */
public abstract class AbstractPercentageTaxStrategy implements ImportTaxStrategy {
    private final ProductCategory category;
    private final BigDecimal dutyRate;
    private final BigDecimal exciseRate;
    private final BigDecimal vatRate;
    private final BigDecimal environmentalFeePerKg;

    protected AbstractPercentageTaxStrategy(
            ProductCategory category,
            BigDecimal dutyRate,
            BigDecimal exciseRate,
            BigDecimal vatRate,
            BigDecimal environmentalFeePerKg
    ) {
        this.category = Objects.requireNonNull(category);
        this.dutyRate = nonNegativeRate(dutyRate, "Ставка мита");
        this.exciseRate = nonNegativeRate(exciseRate, "Ставка акцизу");
        this.vatRate = nonNegativeRate(vatRate, "Ставка ПДВ");
        this.environmentalFeePerKg = nonNegativeRate(environmentalFeePerKg, "Екологічний збір");
    }

    private static BigDecimal nonNegativeRate(BigDecimal rate, String description) {
        Objects.requireNonNull(rate, description + " не може бути null.");
        if (rate.signum() < 0) {
            throw new IllegalArgumentException(description + " не може бути від'ємним.");
        }
        return rate;
    }

    @Override
    public final ProductCategory getCategory() {
        return category;
    }

    @Override
    public TaxBreakdown calculate(ImportGoods goods) {
        Objects.requireNonNull(goods, "Дані товару не можуть бути null.");

        BigDecimal declaredValue = goods.getDeclaredValue();
        BigDecimal importDuty = declaredValue.multiply(dutyRate)
                .add(goods.getWeightKg().multiply(environmentalFeePerKg));
        BigDecimal exciseTax = declaredValue.multiply(exciseRate);

        // За умовою ПДВ нараховується на митну вартість, мито та акциз.
        BigDecimal taxableAmount = declaredValue.add(importDuty).add(exciseTax);
        BigDecimal vatAmount = taxableAmount.multiply(vatRate);

        return new TaxBreakdown(category, importDuty, exciseTax, vatRate, vatAmount);
    }
}

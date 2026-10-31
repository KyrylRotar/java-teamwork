package lab7.task2;

import java.math.BigDecimal;

/** Електроніка: 5% мита, без акцизу, стандартний ПДВ 20%. */
public final class ElectronicsTaxStrategy extends AbstractPercentageTaxStrategy {
    public ElectronicsTaxStrategy() {
        super(
                ProductCategory.ELECTRONICS,
                new BigDecimal("0.05"),
                BigDecimal.ZERO,
                new BigDecimal("0.20"),
                BigDecimal.ZERO
        );
    }
}

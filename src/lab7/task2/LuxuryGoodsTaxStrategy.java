package lab7.task2;

import java.math.BigDecimal;

/** Предмети розкоші: 25% мита, 15% акцизу та стандартний ПДВ 20%. */
public final class LuxuryGoodsTaxStrategy extends AbstractPercentageTaxStrategy {
    public LuxuryGoodsTaxStrategy() {
        super(
                ProductCategory.LUXURY_GOODS,
                new BigDecimal("0.25"),
                new BigDecimal("0.15"),
                new BigDecimal("0.20"),
                BigDecimal.ZERO
        );
    }
}

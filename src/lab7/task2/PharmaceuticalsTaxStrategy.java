package lab7.task2;

import java.math.BigDecimal;

/** Медикаменти: без мита й акцизу, пільговий ПДВ 7%. */
public final class PharmaceuticalsTaxStrategy extends AbstractPercentageTaxStrategy {
    public PharmaceuticalsTaxStrategy() {
        super(
                ProductCategory.PHARMACEUTICALS,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                new BigDecimal("0.07"),
                BigDecimal.ZERO
        );
    }
}

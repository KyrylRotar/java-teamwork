package lab7.task2;

import java.math.BigDecimal;

/** Продукти: 10% мита, $0.50 екологічного збору за кг та пільговий ПДВ 7%. */
public final class FoodTaxStrategy extends AbstractPercentageTaxStrategy {
    public FoodTaxStrategy() {
        super(
                ProductCategory.FOOD,
                new BigDecimal("0.10"),
                BigDecimal.ZERO,
                new BigDecimal("0.07"),
                new BigDecimal("0.50")
        );
    }
}

package lab7.task2;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Знаходить потрібну політику та делегує їй розрахунок.
 * Для додавання категорії не потрібно змінювати калькулятор або його алгоритм.
 */
public final class TaxCalculator {
    private final Map<ProductCategory, ImportTaxStrategy> strategies;

    public TaxCalculator(Collection<? extends ImportTaxStrategy> strategies) {
        Objects.requireNonNull(strategies, "Колекція стратегій не може бути null.");
        if (strategies.isEmpty()) {
            throw new IllegalArgumentException("Потрібно зареєструвати хоча б одну стратегію оподаткування.");
        }

        Map<ProductCategory, ImportTaxStrategy> registeredStrategies = new HashMap<>();
        for (ImportTaxStrategy strategy : strategies) {
            Objects.requireNonNull(strategy, "Стратегія оподаткування не може бути null.");
            ProductCategory category = Objects.requireNonNull(
                    strategy.getCategory(),
                    "Категорія стратегії не може бути null."
            );
            ImportTaxStrategy previous = registeredStrategies.putIfAbsent(category, strategy);
            if (previous != null) {
                throw new IllegalArgumentException(
                        "Для категорії '" + category.getCode() + "' зареєстровано кілька стратегій."
                );
            }
        }

        this.strategies = Map.copyOf(registeredStrategies);
    }

    public TaxBreakdown calculate(ImportGoods goods, ProductCategory category) {
        Objects.requireNonNull(goods, "Дані товару не можуть бути null.");
        Objects.requireNonNull(category, "Категорія товару не може бути null.");

        ImportTaxStrategy strategy = strategies.get(category);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Для категорії '" + category.getCode() + "' не зареєстровано стратегію оподаткування."
            );
        }

        return strategy.calculate(goods);
    }

    /** Зручний метод, якщо виклику потрібна лише загальна сума податків. */
    public BigDecimal calculateTotalTax(ImportGoods goods, ProductCategory category) {
        return calculate(goods, category).getTotalTax();
    }
}

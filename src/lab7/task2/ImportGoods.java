package lab7.task2;

import java.math.BigDecimal;
import java.util.Objects;

/** Незмінні дані товару, зазначені в імпортній декларації. */
public final class ImportGoods {
    private final String goodsCode;
    private final String name;
    private final BigDecimal declaredValue;
    private final BigDecimal weightKg;

    public ImportGoods(String goodsCode, String name, BigDecimal declaredValue, BigDecimal weightKg) {
        if (goodsCode == null || goodsCode.isBlank()) {
            throw new IllegalArgumentException("Код товару не може бути порожнім.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою.");
        }
        this.declaredValue = Objects.requireNonNull(declaredValue, "Задекларована вартість не може бути null.");
        this.weightKg = Objects.requireNonNull(weightKg, "Вага товару не може бути null.");
        if (declaredValue.signum() < 0) {
            throw new IllegalArgumentException("Задекларована вартість не може бути від'ємною.");
        }
        if (weightKg.signum() < 0) {
            throw new IllegalArgumentException("Вага товару не може бути від'ємною.");
        }

        this.goodsCode = goodsCode.trim();
        this.name = name.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }
}

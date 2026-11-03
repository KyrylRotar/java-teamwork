package lab8.task4;

import java.math.BigDecimal;
import java.util.Objects;

public record LoanTerms(BigDecimal deposit, BigDecimal serviceFee) {
    public LoanTerms {
        Objects.requireNonNull(deposit, "Застава обов'язкова.");
        Objects.requireNonNull(serviceFee, "Сервісний збір обов'язковий.");
        if (deposit.signum() < 0 || serviceFee.signum() < 0) {
            throw new IllegalArgumentException("Суми застави та збору не можуть бути від'ємними.");
        }
    }

    public BigDecimal totalPayable() {
        return deposit.add(serviceFee);
    }
}

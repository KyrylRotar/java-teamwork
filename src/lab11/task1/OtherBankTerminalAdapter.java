package lab11.task1;

import lab11.common.EventLog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class OtherBankTerminalAdapter implements PaymentGateway {
    private final OtherBankTerminal terminal;

    public OtherBankTerminalAdapter(OtherBankTerminal terminal) {
        this.terminal = Objects.requireNonNull(terminal, "Платіжний термінал обов'язковий.");
    }

    @Override
    public PaymentResult charge(BigDecimal amountInHryvnias) {
        Objects.requireNonNull(amountInHryvnias, "Сума платежу обов'язкова.");
        if (amountInHryvnias.signum() <= 0) {
            throw new IllegalArgumentException("Сума платежу має бути додатною.");
        }

        long amountInKopiykas;
        try {
            amountInKopiykas = amountInHryvnias
                    .movePointRight(2)
                    .setScale(0, RoundingMode.UNNECESSARY)
                    .longValueExact();
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("Сума платежу не може містити частки копійки.", exception);
        }

        EventLog.log("Адаптер термінала «Обрій»", "Перетворено " + amountInHryvnias
                + " грн на " + amountInKopiykas + " коп.");
        String response = terminal.executeTransaction(amountInKopiykas);
        if ("APPROVED".equals(response)) {
            return PaymentResult.success("Оплату підтверджено.");
        }
        if ("LIMIT_EXCEEDED".equals(response)) {
            return PaymentResult.declined("Банк відхилив оплату: перевищено доступний ліміт.");
        }
        return PaymentResult.declined("Банк відхилив оплату (код " + response + ").");
    }
}

package lab11.task1;

import lab11.common.EventLog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class NewBankTerminalAdapter implements PaymentGateway {
    private final NewBankTerminal terminal;

    public NewBankTerminalAdapter(NewBankTerminal terminal) {
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

        EventLog.log("Адаптер термінала", "Перетворено " + amountInHryvnias
                + " грн на " + amountInKopiykas + " коп.");
        int responseCode = terminal.payInKopiykas(amountInKopiykas);
        if (responseCode == 0) {
            EventLog.log("Адаптер термінала", "Код 0 перетворено на успішну оплату.");
            return PaymentResult.success("Оплату підтверджено.");
        }
        if (responseCode == 51) {
            EventLog.log("Адаптер термінала", "Код 51 перетворено на відмову: недостатньо коштів.");
            return PaymentResult.declined("Недостатньо коштів для оплати.");
        }
        EventLog.log("Адаптер термінала", "Невідомий код " + responseCode + " перетворено на технічну відмову.");
        return PaymentResult.declined("Термінал відхилив оплату (код " + responseCode + ").");
    }
}

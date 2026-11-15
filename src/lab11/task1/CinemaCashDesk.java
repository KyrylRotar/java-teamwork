package lab11.task1;

import lab11.common.EventLog;

import java.math.BigDecimal;
import java.util.Objects;

public final class CinemaCashDesk {
    private PaymentGateway paymentGateway;

    public CinemaCashDesk(PaymentGateway paymentGateway) {
        setPaymentGateway(paymentGateway);
    }

    public void setPaymentGateway(PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway, "Платіжний інтерфейс обов'язковий.");
    }

    public PaymentResult payForTicket(BigDecimal amountInHryvnias) {
        EventLog.log("Каса", "Проводить оплату квитка на " + amountInHryvnias + " грн через PaymentGateway.");
        PaymentResult result = paymentGateway.charge(amountInHryvnias);
        EventLog.log("Каса", result.isSuccessful()
                ? "Результат: успіх. " + result.getMessage()
                : "Результат: відмова. " + result.getMessage());
        return result;
    }
}

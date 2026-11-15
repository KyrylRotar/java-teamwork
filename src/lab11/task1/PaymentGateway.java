package lab11.task1;

import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentResult charge(BigDecimal amountInHryvnias);
}

package lab11.task3;

import lab11.EventLogger;

public class SignLanguageDecorator extends SessionDecorator {
    private static final double SURCHARGE = 10.0;

    public SignLanguageDecorator(MovieSession wrappedSession) {
        super(wrappedSession);
    }

    @Override
    public String getDescription() {
        String baseDesc = super.getDescription();
        EventLogger.log("SignLanguageDecorator", "Обгортка додає модифікацію [Сурдопереклад]");
        return baseDesc + " + Сурдопереклад";
    }

    @Override
    public double getPrice() {
        double currentPrice = super.getPrice();
        EventLogger.log("SignLanguageDecorator", "Обгортка додає націнку +" + SURCHARGE + " грн за сурдопереклад");
        return currentPrice + SURCHARGE;
    }
}
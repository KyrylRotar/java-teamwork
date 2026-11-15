package lab11.task3;

import lab11.EventLogger;

public class QuietSessionDecorator extends SessionDecorator {
    private static final double SURCHARGE = 20.0;

    public QuietSessionDecorator(MovieSession wrappedSession) {
        super(wrappedSession);
    }

    @Override
    public String getDescription() {
        String baseDesc = super.getDescription();
        EventLogger.log("QuietSessionDecorator", "Обгортка додає модифікацію [Тихий сеанс для глядачів з дітьми]");
        return baseDesc + " + Тихий сеанс";
    }

    @Override
    public double getPrice() {
        double currentPrice = super.getPrice();
        EventLogger.log("QuietSessionDecorator", "Обгортка додає націнку +" + SURCHARGE + " грн за тихий сеанс");
        return currentPrice + SURCHARGE;
    }
}
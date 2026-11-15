package lab11.task3;

import lab11.EventLogger;

public class WeekendDiscountDecorator extends SessionDecorator {
    private static final double DISCOUNT_RATE = 0.15;

    public WeekendDiscountDecorator(MovieSession wrappedSession) {
        super(wrappedSession);
    }

    @Override
    public String getDescription() {
        String baseDesc = super.getDescription();
        EventLogger.log("WeekendDiscountDecorator", "Обгортка додає модифікацію [Знижка вихідного дня -15%]");
        return baseDesc + " (Знижка вихідного дня -15%)";
    }

    @Override
    public double getPrice() {
        double currentPrice = super.getPrice();
        double discounted = Math.round(currentPrice * (1 - DISCOUNT_RATE) * 100.0) / 100.0;
        EventLogger.log("WeekendDiscountDecorator", "Обгортка застосовує знижку 15%: " + currentPrice + " -> " + discounted + " грн");
        return discounted;
    }
}
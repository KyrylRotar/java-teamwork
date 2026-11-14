package lab11.task3;

import lab11.EventLogger;

public class ThreeDDecorator extends SessionDecorator {
    private static final double SURCHARGE = 50.0;

    public ThreeDDecorator(MovieSession wrappedSession) {
        super(wrappedSession);
    }

    @Override
    public String getDescription() {
        String baseDesc = super.getDescription();
        EventLogger.log("ThreeDDecorator", "Обгортка додає модифікацію [3D формат]");
        return baseDesc + " + 3D";
    }

    @Override
    public double getPrice() {
        double currentPrice = super.getPrice();
        EventLogger.log("ThreeDDecorator", "Обгортка додає націнку +" + SURCHARGE + " грн за 3D");
        return currentPrice + SURCHARGE;
    }
}
package lab11.task3;

import lab11.EventLogger;

public class SubtitlesDecorator extends SessionDecorator {
    private static final double SURCHARGE = 15.0;

    public SubtitlesDecorator(MovieSession wrappedSession) {
        super(wrappedSession);
    }

    @Override
    public String getDescription() {
        String baseDesc = super.getDescription();
        EventLogger.log("SubtitlesDecorator", "Обгортка додає модифікацію [Субтитри]");
        return baseDesc + " + Субтитри";
    }

    @Override
    public double getPrice() {
        double currentPrice = super.getPrice();
        EventLogger.log("SubtitlesDecorator", "Обгортка додає націнку +" + SURCHARGE + " грн за субтитри");
        return currentPrice + SURCHARGE;
    }
}
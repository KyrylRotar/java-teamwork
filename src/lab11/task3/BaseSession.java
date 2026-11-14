package lab11.task3;

import lab11.EventLogger;

public class BaseSession implements MovieSession {
    private final String movieTitle;
    private final String hall;
    private final double basePrice;

    public BaseSession(String movieTitle, String hall, double basePrice) {
        this.movieTitle = movieTitle;
        this.hall = hall;
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription() {
        EventLogger.log("BaseSession", "Отримання базового опису сеансу: «" + movieTitle + "» (Зал " + hall + ")");
        return "Фільм «" + movieTitle + "» (Зал " + hall + ")";
    }

    @Override
    public double getPrice() {
        EventLogger.log("BaseSession", "Отримання базової ціни: " + basePrice + " грн");
        return basePrice;
    }
}
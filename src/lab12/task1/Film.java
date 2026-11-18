package lab12.task1;

import lab12.EventLogger;

public final class Film implements ProgramComponent {
    private final String title;
    private final int durationMinutes;

    public Film(String title, int durationMinutes) {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Тривалість фільму має бути більшою за нуль.");
        }
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public int getFilmCount() {
        return 1;
    }

    @Override
    public void printTree(String indentation) {
        EventLogger.log("Програма", indentation + "Фільм «" + title + "» — "
                + getDurationMinutes() + " хв, фільмів: " + getFilmCount());
    }
}

package lab9.task1;

import java.time.LocalDateTime;
import java.util.Objects;

/** Сеанс, у межах якого місця мають окремий стан продажу. */
public final class Screening {
    private final String movieTitle;
    private final int roomNumber;
    private final LocalDateTime startsAt;

    public Screening(String movieTitle, int roomNumber, LocalDateTime startsAt) {
        if (movieTitle == null || movieTitle.isBlank()) {
            throw new IllegalArgumentException("Назва фільму не може бути порожньою.");
        }
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Номер залу має бути додатним.");
        }

        this.movieTitle = movieTitle;
        this.roomNumber = roomNumber;
        this.startsAt = Objects.requireNonNull(startsAt, "startsAt");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Screening)) {
            return false;
        }
        Screening screening = (Screening) other;
        return roomNumber == screening.roomNumber
                && movieTitle.equals(screening.movieTitle)
                && startsAt.equals(screening.startsAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, roomNumber, startsAt);
    }
}

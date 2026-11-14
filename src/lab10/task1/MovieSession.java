package lab10.task1;

import java.time.LocalDateTime;
import java.util.Objects;

/** Кіносеанс, на який оформлюється замовлення. */
public record MovieSession(String movieTitle, LocalDateTime startsAt) {
    public MovieSession {
        if (movieTitle == null || movieTitle.isBlank()) {
            throw new IllegalArgumentException("Назва фільму не може бути порожньою.");
        }
        Objects.requireNonNull(startsAt, "Час початку сеансу обов'язковий.");
    }

    @Override
    public String toString() {
        return movieTitle + " (" + startsAt.toString().replace('T', ' ') + ")";
    }
}

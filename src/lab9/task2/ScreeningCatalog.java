package lab9.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Зберігає один налаштований прототип для кожного фільму в кожному залі. */
public final class ScreeningCatalog {
    private final Map<PrototypeKey, ScreeningPrototype> prototypes = new HashMap<>();

    public synchronized ScreeningPrototype getOrCreatePrototype(String movieTitle, int roomNumber) {
        if (movieTitle == null || movieTitle.isBlank()) {
            throw new IllegalArgumentException("Назва фільму не може бути порожньою.");
        }
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Номер залу має бути додатним.");
        }

        PrototypeKey key = new PrototypeKey(movieTitle, roomNumber);
        return prototypes.computeIfAbsent(key, ignored -> {
            System.out.println("Дороге налаштування сеансу: схема залу, ціни та рекламний блок.");
            return ScreeningPrototype.configuredFor(movieTitle, roomNumber);
        });
    }

    private static final class PrototypeKey {
        private final String movieTitle;
        private final int roomNumber;

        private PrototypeKey(String movieTitle, int roomNumber) {
            this.movieTitle = movieTitle;
            this.roomNumber = roomNumber;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PrototypeKey)) {
                return false;
            }
            PrototypeKey key = (PrototypeKey) other;
            return roomNumber == key.roomNumber && movieTitle.equals(key.movieTitle);
        }

        @Override
        public int hashCode() {
            return Objects.hash(movieTitle, roomNumber);
        }
    }
}

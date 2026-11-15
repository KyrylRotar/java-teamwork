package lab11.task2;

import lab11.common.EventLog;

import java.util.List;

public final class CinemaPoster {
    private final List<Trailer> trailers;

    public CinemaPoster(List<Trailer> trailers) {
        this.trailers = List.copyOf(trailers);
    }

    public void open() {
        EventLog.log("Афіша", "Відкрито афішу з " + trailers.size() + " фільмами.");
        for (Trailer trailer : trailers) {
            EventLog.log("Афіша", "Картка фільму «" + trailer.getFilmTitle() + "» готова; файл трейлера не запитується.");
        }
    }

    public Trailer getTrailer(String filmTitle) {
        return trailers.stream()
                .filter(trailer -> trailer.getFilmTitle().equals(filmTitle))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Фільм відсутній в афіші: " + filmTitle));
    }
}

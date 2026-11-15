package lab11.task2;

import lab11.common.EventLog;

import java.util.Objects;

public final class TrailerProxy implements Trailer {
    private final String filmTitle;
    private final int ageLimit;
    private RealTrailer realTrailer;

    public TrailerProxy(String filmTitle, int ageLimit) {
        this.filmTitle = Objects.requireNonNull(filmTitle, "Назва фільму обов'язкова.");
        if (ageLimit < 0) {
            throw new IllegalArgumentException("Вікове обмеження не може бути від'ємним.");
        }
        this.ageLimit = ageLimit;
    }

    @Override
    public String getFilmTitle() {
        return filmTitle;
    }

    @Override
    public void play(Viewer viewer) {
        Objects.requireNonNull(viewer, "Глядач обов'язковий.");
        EventLog.log("Proxy трейлера", "Перевірка доступу до «" + filmTitle + "» для "
                + viewer.getName() + "; вікове обмеження: " + ageLimit + "+.");
        if (ageLimit >= 18 && !viewer.canWatch18Plus()) {
            EventLog.log("Proxy трейлера", "Відмова: Перегляд доступний лише після підтвердження віку.");
            return;
        }

        if (realTrailer == null) {
            EventLog.log("Proxy трейлера", "Трейлер ще не завантажений; створюємо RealTrailer.");
            realTrailer = new RealTrailer(filmTitle);
        } else {
            EventLog.log("Proxy трейлера", "Трейлер уже завантажений; повторного завантаження немає.");
        }
        realTrailer.play(viewer);
    }
}

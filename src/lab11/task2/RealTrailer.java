package lab11.task2;

import lab11.common.EventLog;

public final class RealTrailer implements Trailer {
    private final String filmTitle;

    public RealTrailer(String filmTitle) {
        this.filmTitle = filmTitle;
        EventLog.log("Реальний трейлер", "Завантажено великий відеофайл фільму «" + filmTitle + "».");
    }

    @Override
    public String getFilmTitle() {
        return filmTitle;
    }

    @Override
    public void play(Viewer viewer) {
        EventLog.log("Реальний трейлер", "Об'єкт RealTrailer відтворює трейлер «"
                + filmTitle + "» для глядача " + viewer.getName() + ".");
    }
}

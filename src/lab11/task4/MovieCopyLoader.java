package lab11.task4;

import lab11.EventLogger;

public class MovieCopyLoader {
    public void loadCopy(String movieTitle) {
        EventLogger.log("MovieCopyLoader", "Завантаження DCP-копії фільму «" + movieTitle + "» на сервер залу");
    }
}
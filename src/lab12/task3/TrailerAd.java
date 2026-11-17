package lab12.task3;

import lab12.EventLogger;

public class TrailerAd extends Advertisement {
    private final String releaseDate;

    public TrailerAd(String title, String releaseDate, DisplayMedia media) {
        super(title, media);
        this.releaseDate = releaseDate;
    }

    @Override
    public void show() {
        EventLogger.log("TrailerAd", "Підготовка трейлера «" + title + "» до показу на носії: " + media.getMediaName());
        media.renderContent("Трейлер фільму", title, "У кіно з " + releaseDate);
    }
}
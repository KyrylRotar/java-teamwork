package lab12.task3;

import lab12.EventLogger;

public class SocialAd extends Advertisement {
    private final String organization;

    public SocialAd(String title, String organization, DisplayMedia media) {
        super(title, media);
        this.organization = organization;
    }

    @Override
    public void show() {
        EventLogger.log("SocialAd", "Підготовка соціального ролика («" + organization + "») для носія: " + media.getMediaName());
        media.renderContent("Соціальна реклама", title, "Ініціатива: " + organization);
    }
}
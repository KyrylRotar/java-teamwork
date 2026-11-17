package lab12.task3;

import lab12.EventLogger;

public class PartnerAd extends Advertisement {
    private final String partnerBrand;

    public PartnerAd(String title, String partnerBrand, DisplayMedia media) {
        super(title, media);
        this.partnerBrand = partnerBrand;
    }

    @Override
    public void show() {
        EventLogger.log("PartnerAd", "Підготовка партнерської реклами від [" + partnerBrand + "] для носія: " + media.getMediaName());
        media.renderContent("Реклама партнера", title, "Партнер: " + partnerBrand);
    }
}
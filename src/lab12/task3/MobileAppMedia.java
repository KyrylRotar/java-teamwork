package lab12.task3;

import lab12.EventLogger;

public class MobileAppMedia implements DisplayMedia {
    @Override
    public String getMediaName() {
        return "Мобільний застосунок кінотеатру «Кадр»";
    }

    @Override
    public void renderContent(String adType, String title, String details) {
        EventLogger.log("MobileAppMedia",
                "Показ інтерактивного банера/відео у мобільному застосунку | Тип: [" + adType + "] | Назва: «" + title + "» (" + details + ")");
    }
}
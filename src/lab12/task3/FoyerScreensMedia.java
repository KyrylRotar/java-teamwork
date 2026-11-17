package lab12.task3;

import lab12.EventLogger;

public class FoyerScreensMedia implements DisplayMedia {
    @Override
    public String getMediaName() {
        return "Мережа дисплеїв у фоє кінотеатру";
    }

    @Override
    public void renderContent(String adType, String title, String details) {
        EventLogger.log("FoyerScreensMedia",
                "Трансляція на вертикальних екранах у фоє | Тип: [" + adType + "] | Назва: «" + title + "» (" + details + ")");
    }
}
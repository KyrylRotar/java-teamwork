package lab12.task3;

import lab12.EventLogger;

public class HallScreenMedia implements DisplayMedia {
    @Override
    public String getMediaName() {
        return "Екран кінозали (4K Laser + Dolby Atmos)";
    }

    @Override
    public void renderContent(String adType, String title, String details) {
        EventLogger.log("HallScreenMedia",
                "Відтворення на великому екрані зали | Тип: [" + adType + "] | Назва: «" + title + "» (" + details + ")");
    }
}
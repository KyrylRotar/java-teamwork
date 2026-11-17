package lab12.task3;

import lab12.EventLogger;

public class LedFacadeMedia implements DisplayMedia {
    @Override
    public String getMediaName() {
        return "Зовнішній світлодіодний LED-фасад будівлі";
    }

    @Override
    public void renderContent(String adType, String title, String details) {
        EventLogger.log("LedFacadeMedia",
                "Яскрава широкоформатна трансляція на LED-фасаді | Тип: [" + adType + "] | Назва: «" + title + "» (" + details + ")");
    }
}
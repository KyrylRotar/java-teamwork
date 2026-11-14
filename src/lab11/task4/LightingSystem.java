package lab11.task4;

import lab11.EventLogger;

public class LightingSystem {
    public void dimForAds() {
        EventLogger.log("LightingSystem", "Приглушення основного освітлення залу для показу реклами");
    }

    public void turnOnFullLight() {
        EventLogger.log("LightingSystem", "Увімкнення повного освітлення в залі");
    }
}
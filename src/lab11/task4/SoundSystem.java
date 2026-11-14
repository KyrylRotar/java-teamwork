package lab11.task4;

import lab11.EventLogger;

public class SoundSystem {
    public void calibrate(String profile) {
        EventLogger.log("SoundSystem", "Калібрування звукової системи Dolby Atmos (профіль: " + profile + ")");
    }
}
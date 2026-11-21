package lab13.task1;

import lab13.EventLogger;

public class CinemaHall {
    private boolean lightsOn = true;
    private int volume = 50;
    private boolean paused = false;
    private boolean advertising = false;

    public void setLightsOn(boolean lightsOn) {
        this.lightsOn = lightsOn;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setAdvertising(boolean advertising) {
        this.advertising = advertising;
    }

    public boolean isLightsOn() {
        return lightsOn;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isAdvertising() {
        return advertising;
    }

    public void printState(String source) {
        EventLogger.log(source,
                "стан залу: світло=" + (lightsOn ? "увімкнено" : "вимкнено")
                        + ", гучність=" + volume
                        + ", пауза=" + (paused ? "так" : "ні")
                        + ", реклама=" + (advertising ? "увімкнена" : "вимкнена"));
    }
}

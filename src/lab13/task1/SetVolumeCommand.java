package lab13.task1;

import lab13.EventLogger;

public class SetVolumeCommand implements HallCommand {
    private final CinemaHall hall;
    private final int newVolume;
    private int previousVolume;

    public SetVolumeCommand(CinemaHall hall, int newVolume) {
        this.hall = hall;
        this.newVolume = newVolume;
    }

    @Override
    public void execute() {
        previousVolume = hall.getVolume();
        hall.setVolume(newVolume);
        EventLogger.log(getName(), "гучність змінено з " + previousVolume + " на " + newVolume);
    }

    @Override
    public void undo() {
        hall.setVolume(previousVolume);
        EventLogger.log(getName(), "скасовано: гучність повернено до " + previousVolume);
    }

    @Override
    public String getName() {
        return "SetVolumeCommand";
    }
}

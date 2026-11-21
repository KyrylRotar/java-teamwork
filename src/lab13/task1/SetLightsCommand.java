package lab13.task1;

import lab13.EventLogger;

public class SetLightsCommand implements HallCommand {
    private final CinemaHall hall;
    private final boolean requestedState;
    private boolean previousState;

    public SetLightsCommand(CinemaHall hall, boolean requestedState) {
        this.hall = hall;
        this.requestedState = requestedState;
    }

    @Override
    public void execute() {
        previousState = hall.isLightsOn();
        hall.setLightsOn(requestedState);
        EventLogger.log(getName(), "світло "
                + (requestedState ? "увімкнено" : "вимкнено"));
    }

    @Override
    public void undo() {
        hall.setLightsOn(previousState);
        EventLogger.log(getName(), "скасовано: світло повернуто до "
                + (previousState ? "увімкненого" : "вимкненого"));
    }

    @Override
    public String getName() {
        return "SetLightsCommand";
    }
}

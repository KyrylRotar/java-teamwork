package lab13.task1;

import lab13.EventLogger;

public class PauseCommand implements HallCommand {
    private final CinemaHall hall;
    private final boolean paused;
    private boolean previousPaused;

    public PauseCommand(CinemaHall hall, boolean paused) {
        this.hall = hall;
        this.paused = paused;
    }

    @Override
    public void execute() {
        previousPaused = hall.isPaused();
        hall.setPaused(paused);
        EventLogger.log(getName(), paused ? "показ поставлено на паузу" : "показ відновлено");
    }

    @Override
    public void undo() {
        hall.setPaused(previousPaused);
        EventLogger.log(getName(), "скасовано: попередній стан паузи=" + previousPaused);
    }

    @Override
    public String getName() {
        return "PauseCommand";
    }
}

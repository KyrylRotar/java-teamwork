package lab13.task1;

import lab13.EventLogger;

public class AdvertisingCommand implements HallCommand {
    private final CinemaHall hall;
    private final boolean start;
    private boolean previousAdvertising;

    public AdvertisingCommand(CinemaHall hall, boolean start) {
        this.hall = hall;
        this.start = start;
    }

    @Override
    public void execute() {
        previousAdvertising = hall.isAdvertising();
        hall.setAdvertising(start);
        EventLogger.log(getName(), start ? "реклама запущена" : "реклама зупинена");
    }

    @Override
    public void undo() {
        hall.setAdvertising(previousAdvertising);
        EventLogger.log(getName(), "скасовано: попередній стан реклами=" + previousAdvertising);
    }

    @Override
    public String getName() {
        return "AdvertisingCommand";
    }
}

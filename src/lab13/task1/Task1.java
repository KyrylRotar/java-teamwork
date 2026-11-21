package lab13.task1;

import java.util.Arrays;

import lab13.EventLogger;

public final class Task1 {
    private Task1() {
    }

    public static void run() {
        EventLogger.log("Task1", "=== КОМАНДИ ДЛЯ КЕРУВАННЯ ЗАЛОМ ===");
        CinemaHall hall = new CinemaHall();
        HallOperator operator = new HallOperator();

        operator.execute(new SetVolumeCommand(hall, 35));
        hall.printState("Task1");
        operator.undoLast();
        hall.printState("Task1");

        MacroCommand startMacro = new MacroCommand("Початок сеансу", Arrays.asList(
                new SetLightsCommand(hall, false),
                new SetVolumeCommand(hall, 60),
                new PauseCommand(hall, false),
                new AdvertisingCommand(hall, false)));
        operator.execute(startMacro);
        hall.printState("Task1");

        MacroCommand emergencyMacro = new MacroCommand("Аварійна ситуація", Arrays.asList(
                new PauseCommand(hall, true),
                new SetVolumeCommand(hall, 20),
                new SetLightsCommand(hall, true),
                new AdvertisingCommand(hall, false)));
        operator.execute(emergencyMacro);
        hall.printState("Task1");
    }
}

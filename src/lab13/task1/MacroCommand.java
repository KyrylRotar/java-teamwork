package lab13.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lab13.EventLogger;

public class MacroCommand implements HallCommand {
    private final String name;
    private final List<HallCommand> commands;

    public MacroCommand(String name, List<HallCommand> commands) {
        this.name = name;
        this.commands = new ArrayList<>(commands);
    }

    @Override
    public void execute() {
        EventLogger.log(name, "початок виконання набору");
        for (HallCommand command : commands) {
            command.execute();
        }
        EventLogger.log(name, "набір виконано");
    }

    @Override
    public void undo() {
        EventLogger.log(name, "скасування набору");
        List<HallCommand> reversed = new ArrayList<>(commands);
        Collections.reverse(reversed);
        for (HallCommand command : reversed) {
            command.undo();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

package lab13.task1;

import java.util.ArrayDeque;
import java.util.Deque;

import lab13.EventLogger;

public class HallOperator {
    private final Deque<HallCommand> history = new ArrayDeque<>();

    public void execute(HallCommand command) {
        EventLogger.log("HallOperator", "отримав команду «" + command.getName() + "»");
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            EventLogger.log("HallOperator", "немає команди для скасування");
            return;
        }
        HallCommand command = history.pop();
        EventLogger.log("HallOperator", "скасовує «" + command.getName() + "»");
        command.undo();
    }
}

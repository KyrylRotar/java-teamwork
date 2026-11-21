package lab13.task1;

public interface HallCommand {
    void execute();

    void undo();

    String getName();
}

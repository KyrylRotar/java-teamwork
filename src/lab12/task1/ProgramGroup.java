package lab12.task1;

import lab12.EventLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ProgramGroup implements ProgramComponent {
    private final String name;
    private final List<ProgramComponent> components = new ArrayList<>();

    protected ProgramGroup(String name) {
        this.name = Objects.requireNonNull(name, "Назва групи не може бути null.");
    }

    public void add(ProgramComponent component) {
        components.add(Objects.requireNonNull(component, "Компонент програми не може бути null."));
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int getDurationMinutes() {
        return components.stream().mapToInt(ProgramComponent::getDurationMinutes).sum();
    }

    @Override
    public final int getFilmCount() {
        return components.stream().mapToInt(ProgramComponent::getFilmCount).sum();
    }

    @Override
    public final void printTree(String indentation) {
        EventLogger.log("Програма", indentation + name + " — "
                + getDurationMinutes() + " хв, фільмів: " + getFilmCount());
        for (ProgramComponent component : components) {
            component.printTree(indentation + "  ");
        }
    }
}

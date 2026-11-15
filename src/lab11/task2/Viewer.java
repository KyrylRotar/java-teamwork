package lab11.task2;

public final class Viewer {
    private final String name;
    private final boolean authenticated;
    private final boolean ageConfirmed;
    private final int age;

    public Viewer(String name, boolean authenticated, boolean ageConfirmed, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Вік не може бути від'ємним.");
        }
        this.name = name;
        this.authenticated = authenticated;
        this.ageConfirmed = ageConfirmed;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public boolean canWatch18Plus() {
        return authenticated && ageConfirmed && age >= 18;
    }
}

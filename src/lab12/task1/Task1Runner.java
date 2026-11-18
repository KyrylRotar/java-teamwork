package lab12.task1;

import lab12.EventLogger;

public final class Task1Runner {
    private Task1Runner() {
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        EventLogger.log("Task1Runner", "=== ЗАВДАННЯ 1: ПРОГРАМА КІНОФЕСТИВАЛЮ (COMPOSITE) ===");

        FilmFestival festival = new FilmFestival("Фестиваль «Кадр»");

        ThematicBlock localCinema = new ThematicBlock("Блок «Українське кіно»");
        Screening featureScreening = new Screening("Показ «Додому»");
        featureScreening.add(new Film("Додому", 101));
        localCinema.add(featureScreening);

        Screening shortScreening = new Screening("Показ короткометражок");
        ShortFilmCollection shortFilms = new ShortFilmCollection("Збірка «Міські історії»");
        shortFilms.add(new Film("Ранок", 12));
        shortFilms.add(new Film("Перехрестя", 18));
        shortFilms.add(new Film("Останній трамвай", 15));
        shortScreening.add(shortFilms);
        localCinema.add(shortScreening);

        ThematicBlock worldCinema = new ThematicBlock("Блок «Світове кіно»");
        Screening worldFeature = new Screening("Показ «Мандрівники»");
        worldFeature.add(new Film("Мандрівники", 96));
        worldCinema.add(worldFeature);

        festival.add(localCinema);
        festival.add(worldCinema);

        EventLogger.log("Task1Runner", "--- Програма фестивалю деревом ---");
        festival.printTree("");

        EventLogger.log("Task1Runner", "--- Підсумок ---");
        EventLogger.log("Task1Runner", festival.getName() + ": "
                + festival.getDurationMinutes() + " хв, фільмів: " + festival.getFilmCount());
        EventLogger.log("Task1Runner", localCinema.getName() + ": "
                + localCinema.getDurationMinutes() + " хв, фільмів: " + localCinema.getFilmCount());
        EventLogger.log("Task1Runner", "=== ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО ===");
    }
}

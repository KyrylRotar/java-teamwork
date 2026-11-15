package lab11.task2;

import lab11.common.EventLog;

import java.util.List;

public final class Task2 {
    private Task2() {
    }

    public static void main(String[] args) {
        demonstrateLazyTrailerProxy();
    }

    private static void demonstrateLazyTrailerProxy() {
        EventLog.log("Раннер завдання 2", "Початок демонстрації virtual proxy та контролю віку.");
        CinemaPoster poster = new CinemaPoster(List.of(
                new TrailerProxy("Пригоди у зоряному місті", 12),
                new TrailerProxy("Таємниці старого міста", 0),
                new TrailerProxy("Висота", 16),
                new TrailerProxy("Нічний маршрут", 18),
                new TrailerProxy("Літо на двох", 0),
                new TrailerProxy("Останній сигнал", 18)
        ));

        poster.open();

        Viewer adultViewer = new Viewer("Олена", true, true, 25);
        Trailer regularTrailer = poster.getTrailer("Пригоди у зоряному місті");
        EventLog.log("Глядач", "Запускає трейлер уперше.");
        regularTrailer.play(adultViewer);
        EventLog.log("Глядач", "Запускає той самий трейлер повторно.");
        regularTrailer.play(adultViewer);

        Viewer unauthenticatedViewer = new Viewer("Гість", false, false, 20);
        EventLog.log("Глядач", "Неавторизований глядач запускає трейлер 18+.");
        poster.getTrailer("Нічний маршрут").play(unauthenticatedViewer);
    }
}

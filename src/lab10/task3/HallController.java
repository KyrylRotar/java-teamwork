package lab10.task3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HallController {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String hallName;

    public HallController(String hallName) {
        this.hallName = hallName;
    }

    public void startScreening(String movieTitle, CinemaFormatFactory factory) {
        log("Ініціалізація сеансу '" + movieTitle + "' у форматі [" + factory.getFormatName() + "]...");

        MovieCopy copy = factory.createMovieCopy(movieTitle);
        ProjectorProfile projector = factory.createProjectorProfile();
        SoundProfile sound = factory.createSoundProfile();

        log("Завантажено комплект налаштувань (Формат: " + factory.getFormatName() + "):");
        System.out.println("   • Копія фільму [" + copy.getFormat() + "]: " + copy.getDescription());
        System.out.println("   • Проєктор     [" + projector.getFormat() + "]: " + projector.getConfiguration());
        System.out.println("   • Звук         [" + sound.getFormat() + "]: " + sound.getAudioSetup());
        log("Сеанс успішно запущено! Усі компоненти узгоджені.");
    }

    private void log(String message) {
        System.out.printf("[%s] [%s] %s%n", LocalTime.now().format(TIME_FORMAT), hallName, message);
    }
}
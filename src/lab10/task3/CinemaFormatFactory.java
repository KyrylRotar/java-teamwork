package lab10.task3;

public interface CinemaFormatFactory {
    String getFormatName();
    MovieCopy createMovieCopy(String movieTitle);
    ProjectorProfile createProjectorProfile();
    SoundProfile createSoundProfile();
}
package lab10.task3;

public class ImaxFactory implements CinemaFormatFactory {
    @Override
    public String getFormatName() { return "IMAX Laser"; }

    @Override
    public MovieCopy createMovieCopy(String movieTitle) {
        return new MovieCopy() {
            public String getFormat() { return "IMAX"; }
            public String getDescription() { return "IMAX DMR-ремастеринг '" + movieTitle + "' (співвідношення 1.90:1)"; }
        };
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new ProjectorProfile() {
            public String getFormat() { return "IMAX"; }
            public String getConfiguration() { return "Подвійний 4K лазерний проєктор на всю площу екрана"; }
        };
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new SoundProfile() {
            public String getFormat() { return "IMAX"; }
            public String getAudioSetup() { return "12-канальна некомпресована система IMAX Sound"; }
        };
    }
}
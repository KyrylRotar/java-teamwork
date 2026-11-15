package lab10.task3;

public class DolbyAtmosFactory implements CinemaFormatFactory {
    @Override
    public String getFormatName() { return "Dolby Atmos"; }

    @Override
    public MovieCopy createMovieCopy(String movieTitle) {
        return new MovieCopy() {
            public String getFormat() { return "Dolby Atmos"; }
            public String getDescription() { return "Master DCP '" + movieTitle + "' + об'єктна аудіодоріжка Atmos"; }
        };
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new ProjectorProfile() {
            public String getFormat() { return "Dolby Atmos"; }
            public String getConfiguration() { return "4K HDR лазерна проекція, розширений контраст"; }
        };
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new SoundProfile() {
            public String getFormat() { return "Dolby Atmos"; }
            public String getAudioSetup() { return "64 незалежні аудіоканали + стельові динаміки"; }
        };
    }
}
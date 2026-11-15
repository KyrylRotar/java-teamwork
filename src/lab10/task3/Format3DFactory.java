package lab10.task3;

public class Format3DFactory implements CinemaFormatFactory {
    @Override
    public String getFormatName() { return "3D Стерео"; }

    @Override
    public MovieCopy createMovieCopy(String movieTitle) {
        return new MovieCopy() {
            public String getFormat() { return "3D"; }
            public String getDescription() { return "Стереоскопічний DCP '" + movieTitle + "' (Left/Right Eye, 48 fps)"; }
        };
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new ProjectorProfile() {
            public String getFormat() { return "3D"; }
            public String getConfiguration() { return "Поляризаційний модулятор, підвищена яскравість 28 fL"; }
        };
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new SoundProfile() {
            public String getFormat() { return "3D"; }
            public String getAudioSetup() { return "7.1 Surround з глибинним позиціонуванням"; }
        };
    }
}
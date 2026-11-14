package lab10.task3;

public class Format2DFactory implements CinemaFormatFactory {
    @Override
    public String getFormatName() { return "2D Стандарт"; }

    @Override
    public MovieCopy createMovieCopy(String movieTitle) {
        return new MovieCopy() {
            public String getFormat() { return "2D"; }
            public String getDescription() { return "DCP-пакет '" + movieTitle + "' (2D, 24 fps)"; }
        };
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new ProjectorProfile() {
            public String getFormat() { return "2D"; }
            public String getConfiguration() { return "Стандартний об'єктив, яскравість 14 fL, 2D-режим"; }
        };
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new SoundProfile() {
            public String getFormat() { return "2D"; }
            public String getAudioSetup() { return "Стерео / 5.1 Surround базова сцена"; }
        };
    }
}
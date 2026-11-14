package lab11.task4;

import lab11.EventLogger;

public class CinemaHallFacade {
    private final MovieCopyLoader copyLoader;
    private final ProjectorSystem projector;
    private final SoundSystem sound;
    private final LightingSystem lighting;
    private final TicketSalesSystem ticketSales;
    private final AdPlayerSystem adPlayer;

    public CinemaHallFacade(
            MovieCopyLoader copyLoader,
            ProjectorSystem projector,
            SoundSystem sound,
            LightingSystem lighting,
            TicketSalesSystem ticketSales,
            AdPlayerSystem adPlayer) {
        this.copyLoader = copyLoader;
        this.projector = projector;
        this.sound = sound;
        this.lighting = lighting;
        this.ticketSales = ticketSales;
        this.adPlayer = adPlayer;
    }

    public void prepareSession(String initiator, String sessionId, String movieTitle, String format) {
        EventLogger.log("CinemaHallFacade", "Ініціатор [" + initiator + "] запустив єдину операцію підготовки сеансу #" + sessionId);

        copyLoader.loadCopy(movieTitle);
        projector.configure(format);
        sound.calibrate("Cinema 7.1");
        ticketSales.openSales(sessionId);
        lighting.dimForAds();
        adPlayer.startAds();

        EventLogger.log("CinemaHallFacade", "Сеанс #" + sessionId + " («" + movieTitle + "») повністю готовий до показу");
    }
}
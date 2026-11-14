package lab11.task4;

import lab11.EventLogger;

public class Task4Runner {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        EventLogger.log("Task4Runner", "=== ДЕМОНСТРАЦІЯ ЗАВДАННЯ 4: ПІДГОТОВКА СЕАНСУ (FACADE) ===");

        MovieCopyLoader copyLoader = new MovieCopyLoader();
        ProjectorSystem projector = new ProjectorSystem();
        SoundSystem sound = new SoundSystem();
        LightingSystem lighting = new LightingSystem();
        TicketSalesSystem ticketSales = new TicketSalesSystem();
        AdPlayerSystem adPlayer = new AdPlayerSystem();

        CinemaHallFacade hallFacade = new CinemaHallFacade(
                copyLoader, projector, sound, lighting, ticketSales, adPlayer
        );

        // 1. Адміністратор готує сеанс однією операцією
        EventLogger.log("Task4Runner", "--- Крок 1: Підготовка сеансу адміністратором через Фасад ---");
        hallFacade.prepareSession("Адміністратор залу", "S-101", "Оппенгеймер", "4K Laser");
        System.out.println();

        // 2. Автоматичний розклад готує наступний сеанс тією самою операцією
        EventLogger.log("Task4Runner", "--- Крок 2: Підготовка наступного сеансу автоматичним розкладом ---");
        hallFacade.prepareSession("Автоматичний розклад", "S-102", "Інтерстеллар", "IMAX 3D");
        System.out.println();

        // 3. Окреме ввімкнення світла в залі напряму через підсистему
        EventLogger.log("Task4Runner", "--- Крок 3: Окреме ввімкнення світла в залі напряму через підсистему ---");
        lighting.turnOnFullLight();

        EventLogger.log("Task4Runner", "=== ДЕМОНСТРАЦІЮ ЗАВДАННЯ 4 ЗАВЕРШЕНО ===");
    }
}
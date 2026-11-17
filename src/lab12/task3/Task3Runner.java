package lab12.task3;

import lab12.EventLogger;

public class Task3Runner {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        EventLogger.log("Task3Runner", "=== ДЕМОНСТРАЦІЯ ЗАВДАННЯ 3: РЕКЛАМА ПЕРЕД СЕАНСОМ (BRIDGE) ===");

        // Ініціалізація доступних носіїв
        DisplayMedia hallScreen = new HallScreenMedia();
        DisplayMedia foyerScreens = new FoyerScreensMedia();
        DisplayMedia mobileApp = new MobileAppMedia();

        // 1. Показати трейлер на екрані зали і на екранах у фоє
        EventLogger.log("Task3Runner", "--- Крок 1: Трейлер на екрані зали та на екранах у фоє ---");
        Advertisement duneTrailer = new TrailerAd("Дюна: Месія", "18 грудня", hallScreen);
        duneTrailer.show();
        System.out.println();

        // Змінюємо носій на льоту без створення нового об'єкта реклами
        duneTrailer.setMedia(foyerScreens);
        duneTrailer.show();
        System.out.println();

        // 2. Показати соціальну рекламу в мобільному застосунку
        EventLogger.log("Task3Runner", "--- Крок 2: Соціальна реклама у мобільному застосунку ---");
        Advertisement socialCampaign = new SocialAd("Збережемо карпатські ліси", "Еко-ініціатива Буковини", mobileApp);
        socialCampaign.show();
        System.out.println();

        // 3. Додати носій «світлодіодний фасад» одним класом і показати на ньому рекламу партнера
        EventLogger.log("Task3Runner", "--- Крок 3: Новий носій (LED-фасад) + реклама партнера ---");
        DisplayMedia ledFacade = new LedFacadeMedia();
        Advertisement partnerPromo = new PartnerAd("Комбо-меню з попкорном -20%", "Кадр Бар", ledFacade);
        partnerPromo.show();

        EventLogger.log("Task3Runner", "=== ДЕМОНСТРАЦІЮ ЗАВДАННЯ 3 ЗАВЕРШЕНО ===");
    }
}
package lab12.task2;

public enum SeatCategoryType {
    STANDARD("Стандарт", "Звичайне крісло для комфортного перегляду", 52,
            "standard-seat.png", 180.0),
    COMFORT("Комфорт", "Крісло зі збільшеним простором для ніг", 60,
            "comfort-seat.png", 260.0),
    VIP("VIP", "Преміальне широке крісло з додатковим простором", 72,
            "vip-seat.png", 420.0);

    private final String name;
    private final String description;
    private final int widthCm;
    private final String iconImage;
    private final double basePrice;

    SeatCategoryType(String name, String description, int widthCm, String iconImage, double basePrice) {
        this.name = name;
        this.description = description;
        this.widthCm = widthCm;
        this.iconImage = iconImage;
        this.basePrice = basePrice;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    int getWidthCm() {
        return widthCm;
    }

    String getIconImage() {
        return iconImage;
    }

    double getBasePrice() {
        return basePrice;
    }
}

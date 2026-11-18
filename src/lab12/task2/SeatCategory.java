package lab12.task2;

public final class SeatCategory {
    private final String name;
    private final String description;
    private final int widthCm;
    private final String iconImage;
    private final double basePrice;

    SeatCategory(SeatCategoryType type) {
        this.name = type.getName();
        this.description = type.getDescription();
        this.widthCm = type.getWidthCm();
        this.iconImage = type.getIconImage();
        this.basePrice = type.getBasePrice();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWidthCm() {
        return widthCm;
    }

    public String getIconImage() {
        return iconImage;
    }

    public double getBasePrice() {
        return basePrice;
    }
}

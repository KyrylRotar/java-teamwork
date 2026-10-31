package lab7.task1;

/** Невеликі правила форматування українського тексту, спільні для повідомлення та ваучера. */
final class UkrainianText {
    private UkrainianText() {
    }

    static String nights(long count) {
        long lastTwoDigits = count % 100;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return count + " ночей";
        }

        return switch ((int) (count % 10)) {
            case 1 -> count + " ніч";
            case 2, 3, 4 -> count + " ночі";
            default -> count + " ночей";
        };
    }
}

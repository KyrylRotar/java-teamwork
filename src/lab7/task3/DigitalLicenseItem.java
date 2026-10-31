package lab7.task3;

import java.math.BigDecimal;

/**
 * Цифровий товар каталогу. Він не видає себе за фізичний складський товар,
 * тому не реалізує контракти поповнення чи повернення постачальнику.
 */
public final class DigitalLicenseItem extends AbstractInventoryItem {
    private final String licenseKeyPattern;

    public DigitalLicenseItem(String sku, String title, BigDecimal unitPrice, String licenseKeyPattern) {
        super(sku, title, unitPrice);
        if (licenseKeyPattern == null || licenseKeyPattern.isBlank()) {
            throw new IllegalArgumentException("Шаблон ліцензійного ключа не може бути порожнім.");
        }
        this.licenseKeyPattern = licenseKeyPattern.trim();
    }

    public String getLicenseKeyPattern() {
        return licenseKeyPattern;
    }
}

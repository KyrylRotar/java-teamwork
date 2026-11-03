package lab8.task4;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class LoanPolicyRegistry {
    private final Map<BookCategory, LoanTermsPolicy> policies;

    public LoanPolicyRegistry(Map<BookCategory, ? extends LoanTermsPolicy> policies) {
        Objects.requireNonNull(policies, "Політики видачі обов'язкові.");
        EnumMap<BookCategory, LoanTermsPolicy> indexedPolicies = new EnumMap<>(BookCategory.class);
        for (Map.Entry<BookCategory, ? extends LoanTermsPolicy> entry : policies.entrySet()) {
            indexedPolicies.put(
                    Objects.requireNonNull(entry.getKey(), "Категорія політики обов'язкова."),
                    Objects.requireNonNull(entry.getValue(), "Політика видачі не може бути null.")
            );
        }
        this.policies = Map.copyOf(indexedPolicies);
    }

    public LoanTerms calculate(Book book) {
        Objects.requireNonNull(book, "Книга обов'язкова.");
        LoanTermsPolicy policy = policies.get(book.category());
        if (policy == null) {
            throw new IllegalStateException(
                    "Для категорії " + book.category().getLabel() + " не налаштовано політику видачі."
            );
        }
        return policy.calculate(book);
    }
}

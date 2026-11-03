package lab8.task4;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class LoanPolicyRegistry {
    private final Map<BookCategory, LoanTermsPolicy> policies;

    public LoanPolicyRegistry(Collection<? extends LoanTermsPolicy> policies) {
        Objects.requireNonNull(policies, "Політики видачі обов'язкові.");
        EnumMap<BookCategory, LoanTermsPolicy> indexedPolicies = new EnumMap<>(BookCategory.class);
        for (LoanTermsPolicy policy : policies) {
            Objects.requireNonNull(policy, "Політика видачі не може бути null.");
            if (indexedPolicies.putIfAbsent(policy.category(), policy) != null) {
                throw new IllegalArgumentException(
                        "Для категорії " + policy.category().getLabel() + " задано кілька політик."
                );
            }
        }
        this.policies = Map.copyOf(indexedPolicies);
    }

    public static LoanPolicyRegistry defaults() {
        return new LoanPolicyRegistry(java.util.List.of(
                new StandardLoanTermsPolicy(),
                new RareLoanTermsPolicy(),
                new AcademicLoanTermsPolicy()
        ));
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
